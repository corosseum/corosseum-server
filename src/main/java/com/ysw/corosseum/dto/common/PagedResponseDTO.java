package com.ysw.corosseum.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Schema(description = "페이징 응답")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PagedResponseDTO<T> {

    @Schema(description = "현재 페이지 번호")
    private int page;

    @Schema(description = "페이지 크기")
    private int pageSize;

    @Schema(description = "전체 항목 수")
    private long totalCount;

    @Schema(description = "전체 페이지 수")
    private long totalPage;

    @Schema(description = "데이터 리스트")
    private List<T> data;

    public static <T> PagedResponseDTO<T> of(PagingRequestDTO request, long totalCount, List<T> data) {
        long totalPage = (long) Math.ceil((double) totalCount / request.pageSize());
        totalPage = Math.max(totalPage, 1);
        return new PagedResponseDTO<>(request.page(), request.pageSize(), totalCount, totalPage, data);
    }

    public static <T> PagedResponseDTO<T> empty(PagingRequestDTO request) {
        return new PagedResponseDTO<>(request.page(), request.pageSize(), 0, 0, List.of());
    }
}


