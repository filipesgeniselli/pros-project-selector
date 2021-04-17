package br.com.filisg.prosprojectselector.dtos;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProApplicationExperience {

    @NotNull(message = "Sales experience must be informed")
    private Boolean sales;
    
    @NotNull(message = "Support experience must be informed")
    private Boolean support;

}
