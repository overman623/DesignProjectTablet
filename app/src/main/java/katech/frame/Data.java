package katech.frame;

import java.util.HashMap;

import katech.designprojecttablet.R;


/**
 * Created by bjkim on 2017-07-10.
 */
public class Data {

    public static HashMap<String, Integer> Image = new HashMap<String, Integer>();
    public static HashMap<String, Integer> Id = new HashMap<String, Integer>();

    public Data() {
        setImageData();
        setIdData();
    }

    private void setIdData(){
//        Id.put("타이틀왼쪽", R.id.btn_title_left);
//        Id.put("타이틀오른쪽", R.id.btn_title_right);
    }//지울수도 있음

    private void setImageData(){

        /*로그인 화면 이미지*/
        Image.put("구글", R.drawable.t_login_google);
        Image.put("네이버", R.drawable.t_login_naver);
        Image.put("타이틀", R.drawable.t_logo);/*공통 사용 가능*/
        Image.put("바닥글자", R.drawable.t_intro_smartmobility);/*공통 사용 가능*/
        Image.put("배경", R.drawable.t_intro_background);/*공통 사용 가능*/


        /*모드 선택 전용 이미지*/
        Image.put("모드선택왼쪽화살표", R.drawable.m_mode_choice_arrow_left_off);
        Image.put("모드선택왼쪽화살표_클릭", R.drawable.m_mode_choice_arrow_left_on);
        Image.put("모드선택오른쪽화살표", R.drawable.m_mode_choice_arrow_right_off);
        Image.put("모드선택오른쪽화살표_클릭", R.drawable.m_mode_choice_arrow_right_on);
        Image.put("모드이미지배송", R.drawable.t_intro_modeselect_delivery);
        Image.put("모드이미지헬스케어", R.drawable.t_intro_modeselect_healthy);
        Image.put("모드이미지공유", R.drawable.t_intro_modeselect_share);


        /*메인모드 - 공통*/
        Image.put("공통모드_삼각경고등", R.drawable.t_delivery_main_bottom_warn_off);
        Image.put("공통모드_삼각경고등_클릭", R.drawable.t_delivery_main_bottom_warn_on);
        Image.put("공통모드_길찾기", R.drawable.t_care_main_bottom_navi_btn_off);
        Image.put("공통모드_냉난방", R.drawable.t_care_main_bottom_aircon_btn_off);
        Image.put("공통모드_음악", R.drawable.t_care_main_bottom_music_btn_off);
        Image.put("공통모드_처음화면", R.drawable.t_delivery_main_top_out_btn_off);
        Image.put("공통모드_처음화면_클릭", R.drawable.t_delivery_main_top_out_btn_on);
        Image.put("모드헤드라이트", R.drawable.t_share_main_icon_headlight_off);
        Image.put("모드헤드라이트_클릭", R.drawable.t_share_main_icon_headlight_on);
        Image.put("모드안전벨트", R.drawable.t_share_main_icon__belt_off);
        Image.put("모드안전벨트_클릭", R.drawable.t_share_main_icon__belt_on);
        Image.put("모드문열림", R.drawable.t_share_main_icon__door_off);
        Image.put("모드문열림_클릭", R.drawable.t_share_main_icon__door_on);
        Image.put("아래화살표", R.drawable.t_delivery_bottom_tabmenu_arrow_btn);
        Image.put("볼륨최소", R.drawable.t_delivery_music_volumeicon_minus_on);
        Image.put("볼륨최대", R.drawable.t_delivery_music_volumeicon_plus_off);
        Image.put("냉난방게이지_0_0", R.drawable.t_delivery_aircon_gauge_bg);   //다 찬 이미지
        Image.put("냉난방게이지_0_1", R.drawable.t_care_aircon_gauge_bg_03); //세칸
        Image.put("냉난방게이지_0_2", R.drawable.t_care_aircon_gauge_bg_02); //두칸
        Image.put("냉난방게이지_0_3", R.drawable.t_care_aircon_gauge_bg_01); //한칸
        Image.put("냉난방게이지_0_4", R.drawable.background_none); //없음
        Image.put("냉난방게이지_1_0", R.drawable.t_care_aircon_more_gauge_bg);   //다 찬 이미지
        Image.put("냉난방게이지_1_1", R.drawable.t_care_aircon_more_gauge_bg_03); //세칸
        Image.put("냉난방게이지_1_2", R.drawable.t_care_aircon_more_gauge_bg_02); //두칸
        Image.put("냉난방게이지_1_3", R.drawable.t_care_aircon_more_gauge_bg_01); //한칸
        Image.put("냉난방게이지_1_4", R.drawable.background_none); //없음
        Image.put("안개", R.drawable.t_delivery_aircon_more_now_weather_icon);
        Image.put("앨범커버", R.drawable.t_share_music_albumcover);
        Image.put("마이크", R.drawable.t_share_navi_schbar_voice_icon_btn);
        Image.put("배터리_상태_드라이브", R.drawable.t_aircon_icon);
        Image.put("배터리_상태_냉난방", R.drawable.t_aircon_icon2);
        Image.put("배터리_상태_길찾기", R.drawable.t_aircon_icon3);
        Image.put("배터리_상태_음악", R.drawable.t_aircon_icon4);
        Image.put("그래프", R.drawable.t_aircon_graph);
        Image.put("지도", R.drawable.t_map_image);
        Image.put("지도절반", R.drawable.nevi_half_image);
        Image.put("지도_화살표_위", R.drawable.t_delivery_booking_navi_start_direction01);
        Image.put("지도_화살표_아래", R.drawable.t_delivery_booking_navi_start_direction02);
        Image.put("네비게이션_사운드_배경", R.drawable.t_delivery_navi_schguide_volume_on_gaugebar);

        /*메인모드 - 배송*/
        Image.put("배송모드_홈으로", R.drawable.t_delivery_main_bottom_home_off);
        Image.put("배송모드_홈으로_클릭", R.drawable.t_delivery_main_bottom_home_on);
        Image.put("배송모드_안내", R.drawable.t_delivery_main_bottom_reserve_off);
        Image.put("배송모드_안내_클릭", R.drawable.t_delivery_main_bottom_reserve_on);
        Image.put("배송모드_냉난방_클릭", R.drawable.t_delivery_main_bottom_aircon_btn_on);
        Image.put("배송모드_음악_클릭", R.drawable.t_delivery_main_bottom_music_on);
        Image.put("배송모드_길찾기_클릭", R.drawable.t_delivery_main_bottom_navi_on);
        Image.put("배송모드_판넬", R.drawable.t_delivery_main_speed_bg1);
        Image.put("배송모드_판넬_클릭", R.drawable.t_delivery_main_speed_bg2);
        Image.put("배송모드_좌향등", R.drawable.t_delivery_main_driveleft_off);
        Image.put("배송모드_좌향등_클릭", R.drawable.t_delivery_main_driveleft_on);
        Image.put("배송모드_우향등", R.drawable.t_delivery_main_driveright_off);
        Image.put("배송모드_우향등_클릭", R.drawable.t_delivery_main_driveright_on);
        Image.put("배송모드_배터리", R.drawable.t_delivery_main_battery_charge_btn_off);
        Image.put("배송모드_배터리_클릭", R.drawable.t_delivery_main_battery_charge_btn_on);
        Image.put("배송모드_되감기", R.drawable.t_delivery_music_presong_off);
        Image.put("배송모드_되감기_클릭", R.drawable.t_delivery_music_presong_on);
        Image.put("배송모드_빨리감기", R.drawable.t_delivery_music_nextsong_off);
        Image.put("배송모드_빨리감기_클릭", R.drawable.t_delivery_music_nextsong_on);
        Image.put("배송모드_일시정지", R.drawable.t_delivery_music_play_on);
        Image.put("배송모드_일시정지_클릭", R.drawable.t_delivery_music_play_off);
        Image.put("배송모드_상세보기", R.drawable.t_share_bottom_tabmenu_more_btn_off);
        Image.put("배송모드_상세보기_클릭", R.drawable.t_delivery_aircon_popmenu_more_on);
        Image.put("배송모드_돌기", R.drawable.t_delivery_aircon_popmenu_gauge_controler);
        Image.put("배송모드_냉난방게이지_풀", R.drawable.t_delivery_aircon_gauge_full);
        Image.put("배송모드_냉난방게이지_상세_풀", R.drawable.t_delivery_aircon_more_gauge_full_bg);
        Image.put("배송모드_눈송이버튼", R.drawable.t_delivery_aircon_cool_btn_off);
        Image.put("배송모드_눈송이버튼_클릭", R.drawable.t_delivery_aircon_cool_btn_on);
        Image.put("배송모드_온도계버튼", R.drawable.t_delivery_aircon_heat_btn_off);
        Image.put("배송모드_온도계버튼_클릭", R.drawable.t_delivery_aircon_heat_btn_on);
        Image.put("배송모드_구름", R.drawable.t_delivery_aircon_icon3);
        Image.put("배송모드_바람", R.drawable.t_delivery_aircon_icon1);
        Image.put("배송모드_온도계", R.drawable.t_delivery_aircon_icon2);
        Image.put("배송모드_환기", R.drawable.t_delivery_aircon_more_fan_btn_off);
        Image.put("배송모드_환기_클릭", R.drawable.t_delivery_aircon_more_fan_btn_on);
        Image.put("배송모드_냉방", R.drawable.t_delivery_aircon_more_cool_btn_off);
        Image.put("배송모드_냉방_클릭", R.drawable.t_delivery_aircon_more_cool_btn_on);
        Image.put("배송모드_난방", R.drawable.t_delivery_aircon_more_heat_btn_off);
        Image.put("배송모드_난방_클릭", R.drawable.t_delivery_aircon_more_heat_btn_on);
        Image.put("배송모드_빈_온도계", R.drawable.t_delivery_aircon_more_temp_gauge_bg);
        Image.put("배송모드_검색", R.drawable.t_delivery_navi_search);
        Image.put("배송모드_검색_클릭", R.drawable.t_delivery_navi_search_on);
        Image.put("배송모드_네비게이션_안내창", R.drawable.t_delivery_navi_schguide_popupbg);
        Image.put("배송모드_배터리_잔량", R.drawable.t_delivery_battery_icon1);
        Image.put("배송모드_배터리_길찾기", R.drawable.t_delivery_battery_icon2);
        Image.put("배송모드_배터리_소비현황", R.drawable.t_delivery_battery_icon3);
        Image.put("배송모드_배터리_안내_길찾기", R.drawable.t_delivery_battery_navi_btn_off);
        Image.put("배송모드_배터리_안내_길찾기_클릭", R.drawable.t_delivery_battery_navi_btn_on);
        Image.put("배송모드_충전소_안내창", R.drawable.t_delivery_battery_popup);
        Image.put("배송모드_배터리_사운드키", R.drawable.t_delivery_navi_schguide_volume_off);
        Image.put("배송모드_배터리_사운드키_클릭", R.drawable.t_delivery_navi_schguide_volume_on);
        Image.put("배송모드_네비게이션_완료_안내창", R.drawable.t_delivery_navi_arrivepopup_box);
        Image.put("배송모드_전화하기", R.drawable.t_delivery_booking_list_call_btn_off);
        Image.put("배송모드_전화하기_클릭", R.drawable.t_delivery_booking_list_call_btn_on);
        Image.put("배송모드_큰버튼_전화", R.drawable.t_delivery_navi_finish_popup_calling_btn_off);
        Image.put("배송모드_큰버튼_메시지", R.drawable.t_delivery_navi_finish_popup_mssg_btn_off);
        Image.put("배송모드_큰버튼_배송완료", R.drawable.t_delivery_navi_finish_popup_delivery_btn_off);
        Image.put("배송모드_큰버튼_전화_클릭", R.drawable.t_delivery_navi_finish_popup_calling_btn_on);
        Image.put("배송모드_큰버튼_메시지_클릭", R.drawable.t_delivery_navi_finish_popup_mssg_btn_on);
        Image.put("배송모드_큰버튼_배송완료_클릭", R.drawable.t_delivery_navi_finish_popup_delivery_btn_on);
        Image.put("배송모드_메시지_전송_확인_배경", R.drawable.t_delivery_booking_mssg_confrim_popup_bg2);
        Image.put("배송모드_메시지_전송_완료_배경", R.drawable.t_delivery_navi_schguiupok_on);
        Image.put("배송모드_메시지_경고_배경", R.drawable.t_delivery_warning);



        /*메인모드 - 헬스*/
        Image.put("헬스모드_홈으로", R.drawable.t_delivery_main_bottom_home_off);
        Image.put("헬스모드_홈으로_클릭", R.drawable.t_care_main_bottom_home_btn_on);
        Image.put("헬스모드_안내", R.drawable.t_care_main_bottom_booking_btn_off);
        Image.put("헬스모드_안내_클릭", R.drawable.t_care_main_bottom_booking_btn_on);
        Image.put("헬스모드_냉난방_클릭", R.drawable.t_care_main_bottom_aircon_btn_on);
        Image.put("헬스모드_음악_클릭", R.drawable.t_care_main_bottom_music_btn_on);
        Image.put("헬스모드_길찾기_클릭", R.drawable.t_care_main_bottom_navi_btn_on);
        Image.put("헬스모드_판넬", R.drawable.t_care_main_speed_bg1);
        Image.put("헬스모드_판넬_클릭", R.drawable.t_care_main_speed_bg2);
        Image.put("헬스모드_좌향등", R.drawable.t_delivery_main_driveleft_off);
        Image.put("헬스모드_좌향등_클릭", R.drawable.t_care_main_turnleft_btn_on);
        Image.put("헬스모드_우향등", R.drawable.t_delivery_main_driveright_off);
        Image.put("헬스모드_우향등_클릭", R.drawable.t_care_main_turnright_btn_on);
        Image.put("헬스모드_배터리", R.drawable.t_care_main_battery_charge_btn_off);
        Image.put("헬스모드_배터리_클릭", R.drawable.t_care_main_battery_charge_btn_on);
        Image.put("헬스모드_되감기", R.drawable.t_delivery_music_presong_off);
        Image.put("헬스모드_되감기_클릭", R.drawable.t_care_music_rewind_btn_on);
        Image.put("헬스모드_빨리감기", R.drawable.t_delivery_music_nextsong_off);
        Image.put("헬스모드_빨리감기_클릭", R.drawable.t_care_music_forward_btn_on);
        Image.put("헬스모드_일시정지", R.drawable.t_care_music_pause_btn_on);
        Image.put("헬스모드_일시정지_클릭", R.drawable.t_delivery_music_play_off);
        Image.put("헬스모드_상세보기", R.drawable.t_share_bottom_tabmenu_more_btn_off);
        Image.put("헬스모드_상세보기_클릭", R.drawable.t_care_bottom_tabmenu_more_btn_on);
        Image.put("헬스모드_돌기", R.drawable.t_care_gauge_controler_btn);
        Image.put("헬스모드_냉난방게이지_풀", R.drawable.t_care_aircon_gauge_full);
        Image.put("헬스모드_냉난방게이지_상세_풀", R.drawable.t_care_aircon_more_gauge_full);
        Image.put("헬스모드_눈송이버튼", R.drawable.t_care_aircon_cool_btn_on);
        Image.put("헬스모드_눈송이버튼_클릭", R.drawable.t_care_aircon_cool_btn_on);
        Image.put("헬스모드_온도계버튼", R.drawable.t_delivery_aircon_heat_btn_off);
        Image.put("헬스모드_온도계버튼_클릭", R.drawable.t_care_aircon_heat_btn_on);
        Image.put("헬스모드_구름", R.drawable.t_care_aircon_icon3);
        Image.put("헬스모드_바람", R.drawable.t_care_aircon_icon1);
        Image.put("헬스모드_온도계", R.drawable.t_care_aircon_icon2);
        Image.put("헬스모드_환기", R.drawable.t_delivery_aircon_more_fan_btn_off);
        Image.put("헬스모드_환기_클릭", R.drawable.t_care_aircon_more_fan_btn_on);
        Image.put("헬스모드_냉방", R.drawable.t_delivery_aircon_more_cool_btn_off);
        Image.put("헬스모드_냉방_클릭", R.drawable.t_care_aircon_more_cool_btn_on);
        Image.put("헬스모드_난방", R.drawable.t_delivery_aircon_more_heat_btn_off);
        Image.put("헬스모드_난방_클릭", R.drawable.t_care_aircon_more_heat_btn_on);
        Image.put("헬스모드_빈_온도계", R.drawable.t_care_aircon_more_temp_gauge_bg);
        Image.put("헬스모드_검색", R.drawable.t_delivery_navi_search);
        Image.put("헬스모드_검색_클릭", R.drawable.t_care_navi_schbar_search_icon_btn_on);
        Image.put("헬스모드_네비게이션_안내창", R.drawable.t_care_booking_navi_start_popup_bg2);
        Image.put("헬스모드_배터리_잔량", R.drawable.t_care_battery_icon1);
        Image.put("헬스모드_배터리_길찾기", R.drawable.t_care_battery_icon2);
        Image.put("헬스모드_배터리_소비현황", R.drawable.t_care_battery_icon3);
        Image.put("헬스모드_배터리_안내_길찾기", R.drawable.t_delivery_battery_navi_btn_off);
        Image.put("헬스모드_배터리_안내_길찾기_클릭", R.drawable.t_care_battery_navi_btn_on);
        Image.put("헬스모드_충전소_안내창", R.drawable.t_care_battery_popup);
        Image.put("헬스모드_배터리_사운드키", R.drawable.t_delivery_navi_schguide_volume_off);
        Image.put("헬스모드_배터리_사운드키_클릭", R.drawable.t_care_volume_icon_on);
        Image.put("헬스모드_네비게이션_완료_안내창", R.drawable.t_care_navi_arrivepopup_box);
        Image.put("헬스모드_예약안내_예약정보_안내창", R.drawable.t_care_booking_warning_popup_bg2);

        Image.put("헬스모드_큰버튼_예약목록", R.drawable.t_care_booking_noshow_popup_booklist_btn_off);
        Image.put("헬스모드_큰버튼_전화", R.drawable.t_care_booking_noshow_popup_call_btn_off);
        Image.put("헬스모드_큰버튼_메시지", R.drawable.t_care_booking_noshow_popup_mssg_btn_off);
        Image.put("헬스모드_큰버튼_예약목록_클릭", R.drawable.t_care_booking_noshow_popup_booklist_btn_on);
        Image.put("헬스모드_큰버튼_전화_클릭", R.drawable.t_care_booking_noshow_popup_call_btn_on);
        Image.put("헬스모드_큰버튼_메시지_클릭", R.drawable.t_care_booking_noshow_popup_mssg_btn_on);
        Image.put("헬스모드_탑승자_확인_안내창", R.drawable.t_care_booking_confirm_popup_bg2);
        Image.put("헬스모드_안내시작_확인_안내창", R.drawable.t_care_booking_navi2_popup_bg2);
        Image.put("헬스모드_메시지_전송_확인_배경", R.drawable.t_care_booking_mssg_confrim_popup_bg2);
        Image.put("헬스모드_메시지_전송_완료_배경", R.drawable.t_care_booking_fin);
        Image.put("헬스모드_메시지_경고_배경", R.drawable.t_delivery_warning);


        /*메인모드 - 공유*/
        Image.put("공유모드_홈으로", R.drawable.t_delivery_main_bottom_home_off);
        Image.put("공유모드_홈으로_클릭", R.drawable.t_share_main_bottom_home_btn_on);
        Image.put("공유모드_안내", R.drawable.t_share_main_bottom_booking_btn_off);
        Image.put("공유모드_안내_클릭", R.drawable.t_share_main_bottom_booking_btn_on);
        Image.put("공유모드_냉난방_클릭", R.drawable.t_share_main_bottom_aircon_btn_on);
        Image.put("공유모드_음악_클릭", R.drawable.t_share_main_bottom_music_btn_on);
        Image.put("공유모드_길찾기_클릭", R.drawable.t_share_main_bottom_navi_btn_on);
        Image.put("공유모드_판넬", R.drawable.t_share_main_speed_bg1);
        Image.put("공유모드_판넬_클릭", R.drawable.t_share_main_speed_bg2);
        Image.put("공유모드_좌향등", R.drawable.t_delivery_main_driveleft_off);
        Image.put("공유모드_좌향등_클릭", R.drawable.t_share_main_turnleft_btn_on);
        Image.put("공유모드_우향등", R.drawable.t_delivery_main_driveright_off);
        Image.put("공유모드_우향등_클릭", R.drawable.t_share_main_turnright_btn_on);
        Image.put("공유모드_배터리", R.drawable.t_share_main_battery_charge_btn_off);
        Image.put("공유모드_배터리_클릭", R.drawable.t_share_main_battery_charge_btn_on);
        Image.put("공유모드_되감기", R.drawable.t_delivery_music_presong_off);
        Image.put("공유모드_되감기_클릭", R.drawable.t_share_music_rewind_btn_on);
        Image.put("공유모드_빨리감기", R.drawable.t_delivery_music_nextsong_off);
        Image.put("공유모드_빨리감기_클릭", R.drawable.t_share_music_forward_btn_on);
        Image.put("공유모드_일시정지", R.drawable.t_share_music_pause_btn_on);
        Image.put("공유모드_일시정지_클릭", R.drawable.t_delivery_music_play_off);
        Image.put("공유모드_상세보기", R.drawable.t_share_bottom_tabmenu_more_btn_off);
        Image.put("공유모드_상세보기_클릭", R.drawable.t_share_bottom_tabmenu_more_btn_on);
        Image.put("공유모드_돌기", R.drawable.t_share_gauge_controler_btn);
        Image.put("공유모드_냉난방게이지_풀", R.drawable.t_share_aircon_gauge_full);
        Image.put("공유모드_냉난방게이지_상세_풀", R.drawable.t_share_aircon_more_gauge_full);
        Image.put("공유모드_눈송이버튼", R.drawable.t_delivery_aircon_cool_btn_off);
        Image.put("공유모드_눈송이버튼_클릭", R.drawable.t_share_aircon_cool_btn_on);
        Image.put("공유모드_온도계버튼", R.drawable.t_delivery_aircon_heat_btn_off);
        Image.put("공유모드_온도계버튼_클릭", R.drawable.t_share_aircon_heat_btn_on);
        Image.put("공유모드_구름", R.drawable.t_share_aircon_icon3);
        Image.put("공유모드_바람", R.drawable.t_share_aircon_icon1);
        Image.put("공유모드_온도계", R.drawable.t_share_aircon_icon2);
        Image.put("공유모드_환기", R.drawable.t_delivery_aircon_more_fan_btn_off);
        Image.put("공유모드_환기_클릭", R.drawable.t_share_aircon_more_fan_btn_on);
        Image.put("공유모드_냉방", R.drawable.t_delivery_aircon_more_cool_btn_off);
        Image.put("공유모드_냉방_클릭", R.drawable.t_share_aircon_more_cool_btn_on);
        Image.put("공유모드_난방", R.drawable.t_delivery_aircon_more_heat_btn_off);
        Image.put("공유모드_난방_클릭", R.drawable.t_share_aircon_more_heat_btn_on);
        Image.put("공유모드_빈_온도계", R.drawable.t_share_aircon_more_temp_gauge_bg);
        Image.put("공유모드_검색", R.drawable.t_delivery_navi_search);
        Image.put("공유모드_검색_클릭", R.drawable.t_share_navi_schbar_search_icon_btn_on);
        Image.put("공유모드_네비게이션_안내창", R.drawable.t_share_booking_navi_start_popup_bg2);
        Image.put("공유모드_네비게이션_완료_안내창", R.drawable.t_share_navi_arrivepopup_box);
        Image.put("공유모드_배터리_잔량", R.drawable.t_share_battery_icon1);
        Image.put("공유모드_배터리_길찾기", R.drawable.t_share_battery_icon2);
        Image.put("공유모드_배터리_소비현황", R.drawable.t_delivery_aircon_more_cool_btn_on);
        Image.put("공유모드_배터리_안내_길찾기", R.drawable.t_delivery_battery_navi_btn_off);
        Image.put("공유모드_배터리_안내_길찾기_클릭", R.drawable.t_share_battery_navi_btn_on);
        Image.put("공유모드_충전소_안내창", R.drawable.t_share_battery_popup);
        Image.put("공유모드_배터리_사운드키", R.drawable.t_delivery_navi_schguide_volume_off);
        Image.put("공유모드_배터리_사운드키_클릭", R.drawable.t_share_volume_icon_on);
        Image.put("공유모드_대여시간_추가_안내창", R.drawable.t_share_rental_addtime_reset_popup_bg);
        Image.put("공유모드_대여시간_추가_경고_안내창", R.drawable.t_share_rental_addtime_popup_bg);
        Image.put("공유모드_대여시간_추가_확인_안내창", R.drawable.t_share_rental_addtime_confirm_popup);
        Image.put("공유모드_대여장소_안내창", R.drawable.share_flow_01_main_info_return_dialog); //이미지 없음.
    }

}
