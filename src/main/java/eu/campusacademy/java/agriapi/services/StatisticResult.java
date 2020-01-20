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

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

/**
 * Statistic result for one variety, a location type, a list of location and one year.
 * <p>
 *     Please note that all field are read-only. We do this by only use @Getter annotation and not @Setter.
 *     In this case we also use @AllArgsConstructor.
 * </p>
 *
 * @author <a href="mailto:naudeon50160@gmail.com">Nicolas Audéon</a>
 */
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString(callSuper = true)
public class StatisticResult {

    /**
     * The statistic type
     */
    @Getter
    StatisticType statisticType;

    /**
     * The variety
     */
    @Getter
    String variety;

    /**
     * The location type
     */
    @Getter
    LocationType locationType;

    /**
     * The list of location
     */
    @Getter
    List<String> locations;

    /**
     * The year
     */
    @Getter
    Integer year;

    /**
     * the value of statistic
     */
    @Getter
    BigDecimal value;
}
