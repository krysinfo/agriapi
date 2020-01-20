//==============================================================================
// Copyright 2020 Nicolas Audéon
//
// Redistribution and use in source and binary forms, with or without modification,
// are permitted provided that the following conditions are met :
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice,
// this list of conditions and the following disclaimer in the documentation
// and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
// THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
// CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
// EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
// OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//==============================================================================
package eu.campusacademy.java.agriapi.services;

import eu.campusacademy.java.agriapi.data.repositories.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * The statistics service.
 * <p>
 *     A service contains all functional code and business rules.
 * </p>
 *
 * @author <a href="mailto:naudeon50160@gmail.com">Nicolas Audéon</a>
 * @see <a href="https://dimitr.im/loading-initial-data-with-spring">Spring bean annotations tutorial to know waht @service is</a>
 */
@RequiredArgsConstructor
@Service
public class StatsService {

    private final CropRepository cropRepository;

    /**
     * Obtain and return statistics
     * @param pParams statistics params
     * @return statistics results
     */
    public List<StatisticResult> getStats(final StatisticParams pParams) {
        final List<StatisticResult> results = new LinkedList<>();
        final LocationType locationType = this.getLocationType(pParams);
        pParams.getVarieties().forEach(variety -> calcul(results, variety, locationType, pParams));
        return results;
    }

    /**
     * Check and obtain location type from params of request using this rules
     * <p>
     *     <ul>
     *         <li>if collection of cities code is not null and not empty and collection of department code is null or empty and collection of region code is null or empty the location type is CITY</li>
     *         <li>if collection of cities code is null or empty and collection of department code is not null and not empty and collection of region code is null or empty the location type is DEPARTMENT</li>
     *         <li>if collection of cities code is null or empty and collection of department code is null or empty and collection of region code is not null and not empty the location type is REGION</li>
     *         <li>if all collections are null or empty throw an IllegalArgumentException with messages "All location collections ares null or empty"</li>
     *         <li>else throw an IllegalArgumentException with messages "All location collection ares not null and not empty"</li>
     *     </ul>
     * </p>
     * @param pParams params of request
     * @return location type
     * @exception IllegalArgumentException if all location collections are null or empty or all location collection ares not null and not empty
     */
    private LocationType getLocationType(final StatisticParams pParams) throws IllegalArgumentException {
        LocationType result = null;
        if (isNotNullNorEmpty(pParams.getCityCode()) && isNullOrEmpty(pParams.getDepartmentCode()) && isNullOrEmpty(pParams.getRegionCode())) {
            result = LocationType.CITY;
        } else if (isNullOrEmpty(pParams.getCityCode()) && isNotNullNorEmpty(pParams.getDepartmentCode()) && isNullOrEmpty(pParams.getRegionCode())) {
            result = LocationType.DEPARTMENT;
        } else if (isNullOrEmpty(pParams.getCityCode()) && isNullOrEmpty(pParams.getDepartmentCode()) && isNotNullNorEmpty(pParams.getRegionCode())) {
            result = LocationType.REGION;
        } else {
            if (isNullOrEmpty(pParams.getCityCode()) && isNullOrEmpty(pParams.getDepartmentCode()) && isNullOrEmpty(pParams.getRegionCode())) {
                throw new IllegalArgumentException("All location collections ares null or empty");
            } else {
               throw new IllegalArgumentException("All location collection ares not null and not empty");
            }
        }
        return result;
    }

    /**
     * Check if pCollection is null or empty
     * @param pCollection the collection to check
     * @return <code>true</code> if the collection is null or empty, <code>false</code> otherwise
     */
    private Boolean isNullOrEmpty(final List<String> pCollection) {
        return (null == pCollection || pCollection.isEmpty());
    }

    /**
     * Check if pCollection is not null and not empty
     * @param pCollection the collection to check
     * @return <code>true</code> if the collection is not null and not empty, <code>false</code> otherwise
     */
    private Boolean isNotNullNorEmpty(final List<String> pCollection) {
        return (null != pCollection && !pCollection.isEmpty());
    }

    /**
     * Calcul statistics for a variety, a liste of location and a year/
     * @param pResults the collection of statistics
     * @param pVariety the curent variety
     * @param pLocationType the type of localisation
     * @param pParams request params
     */
    private void calcul(final List<StatisticResult> pResults, final String pVariety, final LocationType pLocationType, final StatisticParams pParams) {
        List<CropInfos> crops = new ArrayList<>();
        List<String> locations = null;
        switch (pLocationType) {
            case CITY:  {
                crops = cropRepository.findAllByVarietyAndCitiesAndYear(pVariety, pParams.getCityCode(), pParams.getYear());
                locations = pParams.getCityCode();
                break;
            }
            case DEPARTMENT: {
                crops = cropRepository.findAllByVarietyAndDepartmentsAndYear(pVariety, pParams.getDepartmentCode(), pParams.getYear());
                locations = pParams.getDepartmentCode();
                break;
            }
            case REGION: {
                crops = cropRepository.findAllByVarietyAndRegionsAndYear(pVariety, pParams.getRegionCode(), pParams.getYear());
                locations = pParams.getRegionCode();
                break;
            }
        }
        pResults.add(new StatisticResult(pParams.getType(), pVariety, pLocationType,  locations, pParams.getYear(), this.getResult(crops, pParams)));
    }

    /**
     * Calcul and return the value of statistic from a list of crops
     * @param pCrops the crops list
     * @param pParams request params
     * @return value of statistic
     */
    private BigDecimal getResult(final List<CropInfos> pCrops, final StatisticParams pParams) {
        BigDecimal result = null;
        List<BigDecimal> yields = new ArrayList<>();
        pCrops.forEach(crop -> {
            BigDecimal qt = crop.getQuantity().multiply(BigDecimal.TEN);
            BigDecimal sh = crop.getSurface();
            yields.add(qt.divide(sh, RoundingMode.UP));
        });
        if(yields.size() > 0) {
            switch (pParams.getType()) {
                case MIN: {
                    result = Collections.min(yields);
                    break;
                }
                case MAX: {
                    result = Collections.max(yields);
                    break;
                }
                case AVERAGE: {
                    BigDecimal sum = yields.stream().filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
                    result = sum.divide(new BigDecimal(yields.size()), RoundingMode.UP);
                    break;
                }
            }
        }
        return result;
    }

}
