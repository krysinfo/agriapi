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

import eu.campusacademy.java.agriapi.data.domain.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * The crop repository
 * <p>
 *    A repository is an interface, that extends JpaRepository or other Spring repositories, and allow access to data.
 *    A repository is a generic element that waits for two parameters, the type of the entity and the type of the entity’s primary key.
 *    There is normally one repository per entity.
 *    The methods defined in this interface are automatically implement by Spring when the application starts.
 *    In our case the usable methods are define by extending the JpaRepository interface
 * </p>
 *
 * @author <a href="mailto:naudeon50160@gmail.com">Nicolas Audéon</a>
 * @see <a href="https://spring.io/guides/gs/spring-boot/">Introduction to Spring boot for more information</a>
 * @see <a href="https://spring.io/guides/gs/accessing-data-jpa/">Acessing Data wit JPA</a>
 */
@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {
}
