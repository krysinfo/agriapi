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
package eu.campusacademy.java.agriapi.data.repositories;

import eu.campusacademy.java.agriapi.data.domain.Crop;
import eu.campusacademy.java.agriapi.services.CropInfos;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The crop repository
 * <p>
 *    A repository is an interface, that extends JpaRepository or other Spring repositories, and allow access to data.
 *    A repository is a generic element that waits for two parameters, the type of the entity and the type of the entity’s primary key.
 *    There is normally one repository per entity.
 *    The methods defined in this interface are automatically implement by Spring when the application starts.
 *    In our case the methods run the native SQL statements given in the @Query annotation.
 *    In the query the parameters are method name parameters which are prefixed by the sign ":".
 * </p>
 *
 * @author <a href="mailto:naudeon50160@gmail.com">Nicolas Audéon</a>
 * @see <a href="https://spring.io/guides/gs/spring-boot/">Introduction to Spring boot for more information</a>
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Acessing Data wit JPA</a>
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    /**
     * Find and return all crops information for a variety, a list of cities and a year.
     * @param pVariety the variety.
     * @param pCities the list of cities.
     * @param pYear the year.
     * @return a list of crops information.
     */
    @Query(nativeQuery = true, value="SELECT c.quantity, p.surface FROM crops c"
            + " INNER JOIN plots p on c.plot_id = p.id"
            + " INNER JOIN farms f on p.farm_id = f.id"
            + " WHERE c.variety = :pVariety"
            + " AND f.city_code IN(:pCities)"
            + " AND c.year = :pYear"
    )
    List<CropInfos> findAllByVarietyAndCitiesAndYear(final String pVariety, final List<String> pCities, final Integer pYear);

    /**
     * Find and return all yield information for à variety, a list of department and a year.
     * @param pVariety the variety.
     * @param pDepartments the list of departments.
     * @param pYear the year.
     * @return a list of crops information.
     */
    @Query(nativeQuery = true, value="SELECT c.quantity, p.surface FROM crops c"
            + " INNER JOIN plots p on c.plot_id = p.id"
            + " INNER JOIN farms f on p.farm_id = f.id"
            + " WHERE c.variety = :pVariety"
            + " AND f.department_code IN(:pDepartments)"
            + " AND c.year = :pYear"
    )
    List<CropInfos> findAllByVarietyAndDepartmentsAndYear(final String pVariety, final List<String> pDepartments, final Integer pYear);

    /**
     * Find and return all yield information for à variety, a list of regions and a year.
     * @param pVariety the variety.
     * @param pRegions the list of regions.
     * @param pYear the year.
     * @return a list of crops information.
     */
    @Query(nativeQuery = true, value="SELECT c.quantity, p.surface FROM crops c"
            + " INNER JOIN plots p on c.plot_id = p.id"
            + " INNER JOIN farms f on p.farm_id = f.id"
            + " WHERE c.variety = :pVariety"
            + " AND f.region_code IN(:pRegions)"
            + " AND c.year = :pYear"
    )
    List<CropInfos> findAllByVarietyAndRegionsAndYear(final String pVariety, final List<String> pRegions, final Integer pYear);

}

