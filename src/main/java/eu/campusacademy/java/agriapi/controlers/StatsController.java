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
package eu.campusacademy.java.agriapi.controlers;

import eu.campusacademy.java.agriapi.services.StatisticParams;
import eu.campusacademy.java.agriapi.services.StatisticResult;
import eu.campusacademy.java.agriapi.services.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A statistics controller.
 * <p>
 *     Please note :
 *     <ul>
 *         <li>we use @RestController annotation to indicate that it is a rest full controller.
 *         As this Spring will automatically process the parameters and return to transform them from or to json.</li>
 *         <li>We use the annotation @RequestMapping to give the root of the URI.
 *         We specify a version what is considered a good practice.</li>
 *         <li>As a controller this class does not contain a business code or management rules.
 *         Its role is to call the service(s) and if necessary check the input parameters / transform the results</li>
 *     </ul>
 * </p>
 *
 * @author <a href="mailto:naudeon50160@gmail.com">Nicolas Audéon</a>
 * @see <a href="https://spring.io/guides/gs/spring-boot/">Introduction to Spring boot for more information</a>
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Acessing Data wit JPA</a>
 */
@RestController
@RequestMapping("/agriapi/v1")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    /**
     * Obtain statistics.
     * @param pParams statistics query params
     * @return statistics
     */
    @GetMapping("/stats")
    public List<StatisticResult> getStats(final @RequestBody StatisticParams pParams) {
        return statsService.getStats(pParams);
    }

}
