package kr.co.imarket_pc.mall.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.imarket_pc.common.RestTemplateApi;
import kr.co.imarket_pc.mall.dto.MallsSpecialtyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MallService {
    private final RestTemplateApi<List> mallsSpecialtyResopnseApi;
    public final ObjectMapper objectMapper;

    public List getMallsSpecialty(MallsSpecialtyRequest mallsSpecialtyRequest) {
        Map<String, String> mallsSpecialtyRequestUriParams = objectMapper.convertValue(mallsSpecialtyRequest, new TypeReference<Map<String, String>>() {
        });
        MultiValueMap<String, String> mallsSpecialtyRequestQueryParams = new LinkedMultiValueMap<>();
        mallsSpecialtyRequestQueryParams.setAll(mallsSpecialtyRequestUriParams);

        return mallsSpecialtyResopnseApi.get("/v1/malls/specialty"
                , null
                , mallsSpecialtyRequestQueryParams
                , List.class);
    }
}
