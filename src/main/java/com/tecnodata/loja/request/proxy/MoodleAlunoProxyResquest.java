package com.tecnodata.loja.request.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoodleAlunoProxyResquest {

    private String wstoken;
    private String wsfunction;
    private String moodlewsrestformat;
    private List<Criteria> criteria = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Criteria {
        private String key;
        private String value;
    }
}
