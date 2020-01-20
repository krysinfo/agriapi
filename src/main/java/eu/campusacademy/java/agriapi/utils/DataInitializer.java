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
package eu.campusacademy.java.agriapi.utils;

import eu.campusacademy.java.agriapi.data.domain.Crop;
import eu.campusacademy.java.agriapi.data.domain.Farm;
import eu.campusacademy.java.agriapi.data.domain.Plot;
import eu.campusacademy.java.agriapi.data.repositories.CropRepository;
import eu.campusacademy.java.agriapi.data.repositories.FarmRepository;
import eu.campusacademy.java.agriapi.data.repositories.PlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 *  Utility component used to populate the database.
 *  <p>
 *     As it implements ApplicationRunner its run method is running automatically by SpringBoot during the application
 *     startup phase.
 *  </p>
 *
 * @author <a href="mailto:naudeon50160@gmail.com">Nicolas Audéon</a>
 * @see <a href="https://dimitr.im/loading-initial-data-with-spring">More methods to load data</a>
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final FarmRepository farmRepository;
    private final PlotRepository plotRepository;
    private final CropRepository cropRepository;

    @Override
    public void run(final ApplicationArguments pArgs) throws Exception {
        if (0 == this.farmRepository.count()) {
            this.farmRepository.save(new Farm(null, "49-85456-98", "49420", "49", "52"));
            this.plotRepository.save(new Plot(null, 1L, new BigDecimal(5)));
            this.cropRepository.save(new Crop(null, 1L, 2017, "ALIXAN",new BigDecimal(34)));
            this.cropRepository.save(new Crop(null, 1L, 2018, "ADVISOR",new BigDecimal(32)));
            this.cropRepository.save(new Crop(null, 1L, 2019, "HENDRIX",new BigDecimal(35)));
            this.farmRepository.save(new Farm(null, "49-85266-32", "49480", "49", "52"));
            this.plotRepository.save(new Plot(null, 2L, new BigDecimal(4)));
            this.cropRepository.save(new Crop(null, 2L, 2017, "HENDRIX",new BigDecimal(20)));
            this.cropRepository.save(new Crop(null, 2L, 2018, "MAORI",new BigDecimal(28)));
            this.cropRepository.save(new Crop(null, 2L, 2019, "ILLICO",new BigDecimal(24)));
            this.plotRepository.save(new Plot(null, 2L, new BigDecimal(17)));
            this.cropRepository.save(new Crop(null, 3L, 2017, "ADVISOR",new BigDecimal(90)));
            this.cropRepository.save(new Crop(null, 3L, 2018, "HENDRIX",new BigDecimal(115)));
            this.cropRepository.save(new Crop(null, 3L, 2019, "MAORI",new BigDecimal(102)));
            this.farmRepository.save(new Farm(null, "53-85456-98", "53800", "53", "52"));
            this.plotRepository.save(new Plot(null, 3L, new BigDecimal(6)));
            this.cropRepository.save(new Crop(null, 4L, 2017, "MAORI",new BigDecimal(42)));
            this.cropRepository.save(new Crop(null, 4L, 2018, "ILLICO",new BigDecimal(32)));
            this.cropRepository.save(new Crop(null, 4L, 2019, "ALIXAN",new BigDecimal(47)));
        }
    }
}
