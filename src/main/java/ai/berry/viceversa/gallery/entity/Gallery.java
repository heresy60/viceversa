package ai.berry.viceversa.gallery.entity;

import ai.berry.viceversa.gallery.payload.GalleryRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Entity(name = "gallery")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Gallery {

    // 고유 키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galContentId;

    // 타입 아이디
    private Integer galContentTypeId;

    // 제목
    @Column(length = 1000)
    private String galTitle;

    // 웹용 이미지 경로
    @Column(length = 1000)
    private String galWebImageUrl;

    // 촬영 월
    @Column(columnDefinition = "char(6)")
    private String galPhotographyMonth;

    // 촬영 장소
    @Column(length = 1000)
    private String galPhotographyLocation;

    // 촬영자
    @Column(length = 30)
    private String galPhotographer;

    // 검색 키워드
    @Column(length = 3000)
    private String galSearchKeyword;

    // 등록일
    @Column(columnDefinition = "char(14)")
    private String galCreatedTime;

    // 수정일
    @Column(columnDefinition = "char(14)")
    private String galModifiedTime;

    /**
     * 저장용 객체 생성 메소드. 생성 날짜는 메소드 호출 시점
     * @param request 저장할 사진 정보
     * @return 생성된 객체 반환
     */
    public static Gallery save(GalleryRequest request) {

        return Gallery.builder()
                .galContentTypeId(request.getGalContentTypeId())
                .galTitle(request.getGalTitle())
                .galWebImageUrl(request.getGalWebImageUrl())
                .galPhotographyMonth(request.getGalPhotographyMonth())
                .galPhotographyLocation(request.getGalPhotographyLocation())
                .galPhotographer(request.getGalPhotographer())
                .galSearchKeyword(request.getGalSearchKeyword())
                .galCreatedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))).build();
    }

    /**
     * 수정 메소드, 수정 날짜는 메소드 호출 시점
     * @param request 수정할 사진 정보
     */
    public void modify(GalleryRequest request) {
        this.galContentTypeId = request.getGalContentTypeId();
        this.galTitle = request.getGalTitle();
        this.galWebImageUrl = request.getGalWebImageUrl();
        this.galPhotographyMonth = request.getGalPhotographyMonth();
        this.galPhotographyLocation = request.getGalPhotographyLocation();
        this.galPhotographer = request.getGalPhotographer();
        this.galSearchKeyword = request.getGalSearchKeyword();
        this.galModifiedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
