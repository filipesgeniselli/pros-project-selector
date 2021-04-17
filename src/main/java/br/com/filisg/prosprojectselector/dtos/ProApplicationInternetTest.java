package br.com.filisg.prosprojectselector.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProApplicationInternetTest {
    
    @JsonProperty("download_speed")
    @NotNull(message = "Download speed must be informed")
    @DecimalMin(value = "0", message = "Download speed must be greater than 0")
    private BigDecimal downloadSpeed;

    @JsonProperty("upload_speed")
    @NotNull(message = "Upload speed must be informed")
    @DecimalMin(value = "0", message = "Upload speed must be greater than 0")
    private BigDecimal uploadSpeed;

}
