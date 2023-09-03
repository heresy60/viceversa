package ai.berry.viceversa.gallery.controller;

import ai.berry.viceversa.gallery.entity.Gallery;
import ai.berry.viceversa.gallery.payload.GalleryRequest;
import ai.berry.viceversa.gallery.payload.GallerySearchRequest;
import ai.berry.viceversa.gallery.service.GalleryService;
import ai.berry.viceversa.gloal.payload.PagingResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(GalleryController.class)
public class GalleryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GalleryService galleryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("사진 등록")
    void saveGallery() throws Exception {

        String title = "한강";

        GalleryRequest galleryRequest = GalleryRequest.builder().galContentTypeId(17)
                .galTitle(title).galWebImageUrl("https://www.naver.com")
                .galPhotographyMonth("202312").galPhotographyLocation(title)
                .galPhotographer("한국인").galSearchKeyword(title).build();

        BDDMockito.given(galleryService.save(galleryRequest))
                        .willReturn(new Gallery(1L, 17, title, "https://www.naver.com"
                        , "202312", title, "한국인", title, "20230903193000", null));

        String content = objectMapper.writeValueAsString(galleryRequest);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/galleries")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(
                        document("post-gallery",
                                requestFields(
                                        fieldWithPath("galContentTypeId").description("타입 번호"),
                                        fieldWithPath("galTitle").description("제목"),
                                        fieldWithPath("galWebImageUrl").description("웹용 이미지 경로"),
                                        fieldWithPath("galPhotographyMonth").description("촬영 월"),
                                        fieldWithPath("galPhotographyLocation").description("촬영 장소"),
                                        fieldWithPath("galPhotographer").description("촬영자"),
                                        fieldWithPath("galSearchKeyword").description("검색 키워드")
                                ),
                                responseFields(
                                        fieldWithPath("galContentId").description("고유 번호"),
                                        fieldWithPath("galContentTypeId").description("타입 번호"),
                                        fieldWithPath("galTitle").description("제목"),
                                        fieldWithPath("galWebImageUrl").description("웹용 이미지 경로"),
                                        fieldWithPath("galPhotographyMonth").description("촬영 월"),
                                        fieldWithPath("galPhotographyLocation").description("촬영 장소"),
                                        fieldWithPath("galPhotographer").description("촬영자"),
                                        fieldWithPath("galSearchKeyword").description("검색 키워드"),
                                        fieldWithPath("galCreatedTime").description("최초 등록일"),
                                        fieldWithPath("galModifiedTime").description("최종 수정일")
                                )
                        )
                );

    }

    @Test
    @DisplayName("사진 수정")
    void modifyGallery() throws Exception {

        String title = "광화문";

        GalleryRequest galleryRequest = GalleryRequest.builder().galContentTypeId(19)
                .galTitle(title).galWebImageUrl("https://www.naver.com")
                .galPhotographyMonth("202312").galPhotographyLocation(title)
                .galPhotographer("중국인").galSearchKeyword(title).build();

        BDDMockito.given(galleryService.modify(1, galleryRequest))
                .willReturn(new Gallery(1L, 19, title, "https://www.naver.com"
                        , "202312", title, "중국인", title, "20230903193000", "20230903193011"));

        String content = objectMapper.writeValueAsString(galleryRequest);
        mockMvc.perform(RestDocumentationRequestBuilders.put("/galleries/{id}", 1)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("put-gallery",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("사진 정보의 고유 번호")
                                ),
                                requestFields(
                                        fieldWithPath("galContentTypeId").description("타입 번호"),
                                        fieldWithPath("galTitle").description("제목"),
                                        fieldWithPath("galWebImageUrl").description("웹용 이미지 경로"),
                                        fieldWithPath("galPhotographyMonth").description("촬영 월"),
                                        fieldWithPath("galPhotographyLocation").description("촬영 장소"),
                                        fieldWithPath("galPhotographer").description("촬영자"),
                                        fieldWithPath("galSearchKeyword").description("검색 키워드")
                                ),
                                responseFields(
                                        fieldWithPath("galContentId").description("고유 번호"),
                                        fieldWithPath("galContentTypeId").description("타입 번호"),
                                        fieldWithPath("galTitle").description("제목"),
                                        fieldWithPath("galWebImageUrl").description("웹용 이미지 경로"),
                                        fieldWithPath("galPhotographyMonth").description("촬영 월"),
                                        fieldWithPath("galPhotographyLocation").description("촬영 장소"),
                                        fieldWithPath("galPhotographer").description("촬영자"),
                                        fieldWithPath("galSearchKeyword").description("검색 키워드"),
                                        fieldWithPath("galCreatedTime").description("최초 등록일"),
                                        fieldWithPath("galModifiedTime").description("최종 수정일")
                                )
                        )
                );

    }

    @Test
    @DisplayName("사진 삭제")
    void deleteGallery() throws Exception {

        mockMvc.perform(RestDocumentationRequestBuilders.delete("/galleries/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(
                        document("delete-gallery",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("사진 정보의 고유 번호")
                                )));

    }

    @Test
    @DisplayName("사진 단일 조회")
    void findGallery() throws Exception {

        String title = "광화문";

        BDDMockito.given(galleryService.findById(1))
                .willReturn(new Gallery(1L, 19, title, "https://www.naver.com"
                        , "202312", title, "중국인", title, "20230903193000", null));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/galleries/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("get-gallery",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("사진 정보의 고유 번호")
                                ),
                                responseFields(
                                        fieldWithPath("galContentId").description("고유 번호"),
                                        fieldWithPath("galContentTypeId").description("타입 번호"),
                                        fieldWithPath("galTitle").description("제목"),
                                        fieldWithPath("galWebImageUrl").description("웹용 이미지 경로"),
                                        fieldWithPath("galPhotographyMonth").description("촬영 월"),
                                        fieldWithPath("galPhotographyLocation").description("촬영 장소"),
                                        fieldWithPath("galPhotographer").description("촬영자"),
                                        fieldWithPath("galSearchKeyword").description("검색 키워드"),
                                        fieldWithPath("galCreatedTime").description("최초 등록일"),
                                        fieldWithPath("galModifiedTime").description("최종 수정일")
                                )
                        )
                );

    }

    @Test
    @DisplayName("사진 목록 조회")
    void findGalleries() throws Exception {

        String title = "광화문";

        Pageable pageable = PageRequest.of(0,5);
        GallerySearchRequest request= GallerySearchRequest.builder().galTitle("광화문").build();

        List<Gallery> data = List.of(new Gallery(1L, 19, title, "https://www.naver.com", "202312", title, "중국인", title, "20230903193000", null));

        Page<Gallery> result = new PageImpl<>(data, pageable, 1);

        BDDMockito.given(galleryService.findAll(request, pageable))
                .willReturn(result);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/galleries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "5")
                        .param("galTitle", "광화문")
                )
                .andExpect(status().isOk())
                .andDo(
                        document("get-galleries",
                                queryParameters(
                                        parameterWithName("page").description("조회 하려는 페이지 번호. [ 0 부터 시작 ]").optional(),
                                        parameterWithName("size").description("한번에 조회 할 데이터 수").optional(),
                                        parameterWithName("sort").description("정렬 할 항목 [ DESC | ASC ]").optional(),
                                        parameterWithName("galContentTypeId").description("타입 번호").optional(),
                                        parameterWithName("galTitle").description("제목").optional(),
                                        parameterWithName("galPhotographyMonth").description("촬영 월").optional(),
                                        parameterWithName("galPhotographyLocation").description("촬영 장소").optional(),
                                        parameterWithName("galPhotographer").description("촬영자").optional(),
                                        parameterWithName("galSearchKeyword").description("검색 키워드").optional()
                                ),
                                responseFields(
                                        fieldWithPath("totalCount").description("전체 데이터 수"),
                                        fieldWithPath("data[].galContentId").description("고유 번호"),
                                        fieldWithPath("data[].galContentTypeId").description("타입 번호"),
                                        fieldWithPath("data[].galTitle").description("제목"),
                                        fieldWithPath("data[].galWebImageUrl").description("웹용 이미지 경로"),
                                        fieldWithPath("data[].galPhotographyMonth").description("촬영 월"),
                                        fieldWithPath("data[].galPhotographyLocation").description("촬영 장소"),
                                        fieldWithPath("data[].galPhotographer").description("촬영자"),
                                        fieldWithPath("data[].galSearchKeyword").description("검색 키워드"),
                                        fieldWithPath("data[].galCreatedTime").description("최초 등록일"),
                                        fieldWithPath("data[].galModifiedTime").description("최종 수정일")
                                )
                        )
                );

    }
}
