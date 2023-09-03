package ai.berry.viceversa.gallery.controller;

import ai.berry.viceversa.gallery.entity.Gallery;
import ai.berry.viceversa.gallery.payload.GalleryRequest;
import ai.berry.viceversa.gallery.service.GalleryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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
                        document("post-galleries",
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
                        , "202312", title, "중국인", title, "20230903193000", null));

        String content = objectMapper.writeValueAsString(galleryRequest);
        mockMvc.perform(RestDocumentationRequestBuilders.put("/galleries/{id}", 1)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("put-galleries",
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
                        document("delete-galleries",
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
                        document("get-galleries",
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
}
