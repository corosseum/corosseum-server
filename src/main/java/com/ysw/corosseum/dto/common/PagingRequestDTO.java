package com.ysw.corosseum.dto.common;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.RequestParam;

@Schema(description = "페이징 요청 DTO")
@ParameterObject
public record PagingRequestDTO(
    @Parameter(example = "1", description = "페이지 번호")
    @RequestParam(defaultValue = "1")
    int page,

    @Parameter(example = "10", description = "페이지 크기")
    @RequestParam(defaultValue = "10")
    int pageSize
) {
    public int getOffset() {
        return (page() - 1) * pageSize();
    }

    public int getLimit() {
        return pageSize();
    }
}


