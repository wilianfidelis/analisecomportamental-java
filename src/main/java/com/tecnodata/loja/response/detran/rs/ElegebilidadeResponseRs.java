package com.tecnodata.loja.response.detran.rs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElegebilidadeResponseRs {
    private String codTicket;
    private String renach;
    private String codErro;
    private String msgErro;
}
