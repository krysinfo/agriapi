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
package eu.campusacademy.java.agriapi.data.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * A farm entity
 * <p>
 *     An entity is a class who is mapped to a table in a database.
 *     An entity has a unique id.
 *     We use JPA annotation like @Entity or @Table to indicate to Hibernate how to do the mapping.
 *     In this project we use Lombok to write less code with a fabulous set of annotation like @AllArgsConstructor
 * </p>
 *
 * @author <a href="mailto:naudeon50160@gmail.com">Nicolas Audéon</a>
 * @see <a href="https://spring.io/guides/gs/spring-boot/">Introduction to Spring boot for more information</a>
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Acessing Data wit JPA</a>
 */
@Entity
@Table(name = "farms")
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Farm {

    /**
     * the farm generated unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    Long id;

    /**
     * the farm pacage code
     */
    @Getter
    @Setter
    String pacageCode;

    /**
     * The city code of the farm
     */
    @Getter
    @Setter
    String cityCode;

    /**
     * The department code of the farm
     */
    @Getter
    @Setter
    String departmentCode;

    /**
     * The region code of the farm
     */
    @Getter
    @Setter
    String regionCode;

}
