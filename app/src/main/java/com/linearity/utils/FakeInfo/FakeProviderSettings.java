package com.linearity.utils.FakeInfo;

import static com.linearity.utils.FakeInfo.FakeProcInfoGenerator.random;
import static com.linearity.utils.ReturnReplacements.getRandomHexlower;
import static com.linearity.utils.ReturnReplacements.getRandomString;
import static com.linearity.utils.ReturnReplacements.randomSmallDouble;

import java.util.HashMap;
import java.util.Map;

public class FakeProviderSettings {
    public static final Map<String,String> systemSettingsMap = new HashMap<>();
    //String[] keys = {
    ////                                "lock_pattern_tactile_feedback_enabled",
    //                                "car_undock_sound","media_button_receiver",
    //                                "ring_vibration_intensity","network_preference","window_animation_scale","wifi_mobile_data_transition_wakelock_timeout_ms",
    //                                "wifi_static_dns1","wifi_static_dns2","bluetooth_discoverability_timeout","alarm_alert_cache",
    //                                "mode_ringer","notification_light_pulse","when_to_make_wifi_calls","wifi_networks_available_notification_on",
    //                                "location_providers_allowed","notification_sound","advanced_settings","wifi_static_netmask",
    //                                "notification_sound_cache","bluetooth_on","next_alarm_formatted","volume_alarm",
    //                                "volume_music","volume_voice","show_password","wifi_watchdog_on",
    //                                "wifi_watchdog_ping_timeout_ms","SHOW_GTALK_SERVICE_STATUS","ringtone","adaptive_sleep",
    //                                "haptic_feedback_intensity","show_web_suggestions","wallpaper_activity","wifi_watchdog_background_check_timeout_ms",
    //                                "parental_control_last_update","auto_replace","hearing_aid","ringtone_cache",
    //                                "wifi_watchdog_max_ap_checks","debug.enable_enhanced_calling","lock_to_app_enabled","sip_call_options",
    //                                "adb_enabled","unlock_sound","multi_audio_focus_enabled","parental_control_enabled",
    //                                "airplane_mode_radios","volume_a11y","volume_ring","wifi_max_dhcp_retry_count",
    //                                "vibrate_in_silent","settings_classname","screen_auto_brightness_adj","always_finish_activities",
    //                                "notifications_use_ring_volume","airplane_mode_toggleable_radios","screen_brightness_mode","mute_streams_affected",
    //                                "lockscreen.disabled","tty_mode","screen_brightness_for_vr","font_scale",
    //                                "wifi_watchdog_ping_delay_ms","dim_screen","end_button_behavior","wifi_watchdog_ping_count",
    //                                "desk_undock_sound","debug_app","wifi_sleep_policy","dock_sounds_enabled",
    //                                "egg_mode","SIP_ASK_ME_EACH_TIME","volume_bluetooth_sco","screen_brightness_for_vr_float",
    //                                "screen_brightness_float","lockscreen_sounds_enabled","nfc","cell",
    //                                "wifi","user_rotation","accelerometer_rotation","SIP_ALWAYS",
    //                                "wimax",
    ////                                "display_color_mode_vendor_hint",
    //                                "wifi_watchdog_background_check_delay_ms","auto_punctuate",
    //                                "car_dock_sound","screen_off_timeout","show_touches","vibrate_when_ringing",
    //                                "power_sounds_enabled","stay_on_while_plugged_in","hide_rotation_lock_toggle_for_accessibility","alarm_alert",
    //                                "wifi_watchdog_initial_ignored_ping_count","pointer_speed","wifi_networks_available_repeat_delay","wifi_static_ip",
    //                                "transition_animation_scale","wifi_watchdog_background_check_enabled","vibrate_input_devices","logging_id",
    //                                "mode_ringer_streams_affected","data_roaming","android_id","volume_notification",
    ////                                "accelerometer_rotation_angles",
    //                                "wifi_static_gateway",
    ////                                "lock_pattern_autolock",
    //                                "dtmf_tone_type",
    //                                "dtmf_tone","airplane_mode_on","low_battery_sound","date_format",
    //                                "volume_master","wait_for_debugger","volume_assistant","peak_refresh_rate",
    //                                "wifi_watchdog_ap_count","time_12_24","pointer_location","system_locales",
    //                                "bluetooth_discoverability","volume_system","master_mono","lock_sound",
    //                                "status_bar_show_battery_percent","auto_time_zone","http_proxy","parental_control_redirect_url",
    //                                "show_processes","sip_receive_calls","wifi_on","device_provisioned",
    //                                "sound_effects_enabled","master_balance",
    ////                                "lock_pattern_visible_pattern",
    //                                "display_color_mode",
    //                                "volume_key_cursor_control","wifi_use_static_ip","auto_caps","auto_time",
    //                                "install_non_market_apps","usb_mass_storage_enabled","wifi_num_open_networks_kept","screen_brightness",
    //                                "use_google_mail","vibrate_on","wifi_watchdog_acceptable_packet_loss_percentage","min_refresh_rate",
    //                                "bluetooth","SIP_ADDRESS_ONLY","notification_vibration_intensity","haptic_feedback_enabled",
    //                                "desk_dock_sound","setup_wizard_has_run","window_orientation_listener_log","_last_audible",
    //                                "animator_duration_scale",
    //                        };
    static {
        systemSettingsMap.put("car_undock_sound",getRandomString(10));//"/product/media/audio/ui/Undock.ogg"
        systemSettingsMap.put("media_button_receiver",null);
        systemSettingsMap.put("ring_vibration_intensity",null);
        systemSettingsMap.put("network_preference",null);
        systemSettingsMap.put("window_animation_scale",String.valueOf(1.0 + randomSmallDouble(1.0)));
        systemSettingsMap.put("wifi_mobile_data_transition_wakelock_timeout_ms",null);
        systemSettingsMap.put("wifi_static_dns1",null);
        systemSettingsMap.put("wifi_static_dns2",null);
        systemSettingsMap.put("bluetooth_discoverability_timeout",null);
        systemSettingsMap.put("alarm_alert_cache",null);
        systemSettingsMap.put("mode_ringer","0");
        systemSettingsMap.put("notification_light_pulse","1");
        systemSettingsMap.put("when_to_make_wifi_calls",null);
        systemSettingsMap.put("wifi_networks_available_notification_on",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("location_providers_allowed",null);
        systemSettingsMap.put("notification_sound",getRandomString(10));//"content://media/internal/audio/media/183?title=Argon&canonical=1"
        systemSettingsMap.put("advanced_settings",getRandomString(10));//null
        systemSettingsMap.put("wifi_static_netmask",getRandomString(10));//null
        systemSettingsMap.put("notification_sound_cache",getRandomString(10));//null
        systemSettingsMap.put("bluetooth_on",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("next_alarm_formatted",getRandomString(10));//"周五13:30"
        systemSettingsMap.put("volume_alarm",String.valueOf(1+random.nextInt(10)));//"6"
        systemSettingsMap.put("volume_music",String.valueOf(1+random.nextInt(10)));//5
        systemSettingsMap.put("volume_voice",String.valueOf(1+random.nextInt(10)));//4
        systemSettingsMap.put("show_password",null);
        systemSettingsMap.put("wifi_watchdog_on",null);
        systemSettingsMap.put("wifi_watchdog_ping_timeout_ms",null);
        systemSettingsMap.put("SHOW_GTALK_SERVICE_STATUS",null);
        systemSettingsMap.put("ringtone",getRandomString(10));//"content://0@media/external/audio/media/565?title=%E6%83%91%E6%98%9FFloating&canonical=1"
        systemSettingsMap.put("adaptive_sleep",null);
        systemSettingsMap.put("haptic_feedback_intensity",null);
        systemSettingsMap.put("show_web_suggestions",null);
        systemSettingsMap.put("wallpaper_activity",null);
        systemSettingsMap.put("wifi_watchdog_background_check_timeout_ms",null);
        systemSettingsMap.put("parental_control_last_update",null);
        systemSettingsMap.put("auto_replace",null);
        systemSettingsMap.put("hearing_aid",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("ringtone_cache",null);
        systemSettingsMap.put("wifi_watchdog_max_ap_checks",null);
        systemSettingsMap.put("debug.enable_enhanced_calling",null);
        systemSettingsMap.put("lock_to_app_enabled",null);
        systemSettingsMap.put("sip_call_options",null);
        systemSettingsMap.put("adb_enabled","0");
        systemSettingsMap.put("unlock_sound",getRandomString(10));//"/product/media/audio/ui/Unlock.ogg"
        systemSettingsMap.put("multi_audio_focus_enabled",null);
        systemSettingsMap.put("parental_control_enabled",null);
        systemSettingsMap.put("airplane_mode_radios",getRandomString(10));//"cell,bluetooth,wifi,nfc,wimax"
        systemSettingsMap.put("volume_a11y",null);
        systemSettingsMap.put("volume_ring",String.valueOf(1+random.nextInt(10)));//5
        systemSettingsMap.put("wifi_max_dhcp_retry_count",String.valueOf(1+random.nextInt(10)));//9
        systemSettingsMap.put("vibrate_in_silent",null);
        systemSettingsMap.put("settings_classname",null);
        systemSettingsMap.put("screen_auto_brightness_adj","0.0");
        systemSettingsMap.put("always_finish_activities","0");
        systemSettingsMap.put("notifications_use_ring_volume",null);
        systemSettingsMap.put("airplane_mode_toggleable_radios",getRandomString(10));//"bluetooth,wifi,nfc"
        systemSettingsMap.put("screen_brightness_mode",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("mute_streams_affected",String.valueOf(30+random.nextInt(100)));//111
        systemSettingsMap.put("lockscreen.disabled",null);
        systemSettingsMap.put("tty_mode","0");
        systemSettingsMap.put("screen_brightness_for_vr",String.valueOf(30+random.nextInt(100)));//"86"
        systemSettingsMap.put("font_scale",String.valueOf(1.0 + randomSmallDouble(1.0)));
        systemSettingsMap.put("wifi_watchdog_ping_delay_ms",null);
        systemSettingsMap.put("dim_screen","1");
        systemSettingsMap.put("end_button_behavior","2");
        systemSettingsMap.put("wifi_watchdog_ping_count",null);
        systemSettingsMap.put("desk_undock_sound",getRandomString(10));//"/product/media/audio/ui/Undock.ogg"
        systemSettingsMap.put("debug_app",null);
        systemSettingsMap.put("wifi_sleep_policy",String.valueOf(1+random.nextInt(10)));//2
        systemSettingsMap.put("dock_sounds_enabled",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("egg_mode",null);
        systemSettingsMap.put("SIP_ASK_ME_EACH_TIME",null);
        systemSettingsMap.put("volume_bluetooth_sco",String.valueOf(1+random.nextInt(10)));//7
        systemSettingsMap.put("screen_brightness_for_vr_float",null);
        systemSettingsMap.put("screen_brightness_float",null);
        systemSettingsMap.put("lockscreen_sounds_enabled",String.valueOf(random.nextBoolean()?0:1));//1
        systemSettingsMap.put("nfc",null);
        systemSettingsMap.put("cell",null);
        systemSettingsMap.put("wifi",getRandomString(20));
        systemSettingsMap.put("user_rotation","0");
        systemSettingsMap.put("accelerometer_rotation","0");
        systemSettingsMap.put("SIP_ALWAYS",null);
        systemSettingsMap.put("wimax",null);
        systemSettingsMap.put("wifi_watchdog_background_check_delay_ms",null);
        systemSettingsMap.put("auto_punctuate",null);
        systemSettingsMap.put("car_dock_sound",getRandomString(10));//"/product/media/audio/ui/Dock.ogg");
        systemSettingsMap.put("screen_off_timeout",String.valueOf(Math.abs(random.nextLong())));//"1800000"
        systemSettingsMap.put("show_touches",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("vibrate_when_ringing",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("power_sounds_enabled",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("stay_on_while_plugged_in",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("hide_rotation_lock_toggle_for_accessibility","0");
        systemSettingsMap.put("alarm_alert",getRandomString(10));//"content://media/internal/audio/media/235?title=Hassium&canonical=1"
        systemSettingsMap.put("wifi_watchdog_initial_ignored_ping_count",null);
        systemSettingsMap.put("pointer_speed",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("wifi_networks_available_repeat_delay",null);
        systemSettingsMap.put("wifi_static_ip",null);
        systemSettingsMap.put("transition_animation_scale",String.valueOf(1.0 + randomSmallDouble(1.0)));
        systemSettingsMap.put("wifi_watchdog_background_check_enabled",null);
        systemSettingsMap.put("vibrate_input_devices",null);
        systemSettingsMap.put("logging_id",null);
        systemSettingsMap.put("mode_ringer_streams_affected",String.valueOf(30+random.nextInt(422)));//422
        systemSettingsMap.put("data_roaming",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("android_id",getRandomHexlower(16));//"9280b87beb8d277c"
        systemSettingsMap.put("volume_notification",String.valueOf(1+random.nextInt(10)));
        systemSettingsMap.put("wifi_static_gateway",null);
        systemSettingsMap.put("dtmf_tone_type","0");
        systemSettingsMap.put("dtmf_tone","0");
        systemSettingsMap.put("airplane_mode_on","0");
        systemSettingsMap.put("low_battery_sound",getRandomString(10));//"/product/media/audio/ui/LowBattery.ogg"
        systemSettingsMap.put("date_format",null);
        systemSettingsMap.put("volume_master",null);
        systemSettingsMap.put("wait_for_debugger","0");
        systemSettingsMap.put("volume_assistant",null);
        systemSettingsMap.put("peak_refresh_rate",null);
        systemSettingsMap.put("wifi_watchdog_ap_count",null);
        systemSettingsMap.put("time_12_24","24");
        systemSettingsMap.put("pointer_location","0");
        systemSettingsMap.put("system_locales","zh-CN");
        systemSettingsMap.put("bluetooth_discoverability",null);
        systemSettingsMap.put("volume_system",String.valueOf(1+random.nextInt(10)));
        systemSettingsMap.put("master_mono",null);
        systemSettingsMap.put("lock_sound",getRandomString(10));//"/product/media/audio/ui/Lock.ogg"
        systemSettingsMap.put("status_bar_show_battery_percent",null);
        systemSettingsMap.put("auto_time_zone","1");
        systemSettingsMap.put("http_proxy",null);
        systemSettingsMap.put("parental_control_redirect_url",null);
        systemSettingsMap.put("show_processes",null);
        systemSettingsMap.put("sip_receive_calls",null);
        systemSettingsMap.put("wifi_on",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("device_provisioned",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("sound_effects_enabled",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("master_balance",null);
        systemSettingsMap.put("display_color_mode",null);
        systemSettingsMap.put("volume_key_cursor_control",null);
        systemSettingsMap.put("wifi_use_static_ip",null);
        systemSettingsMap.put("auto_caps",null);
        systemSettingsMap.put("auto_time",String.valueOf(random.nextBoolean()?0:1));//1
        systemSettingsMap.put("install_non_market_apps",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("usb_mass_storage_enabled",String.valueOf(random.nextBoolean()?0:1));
        systemSettingsMap.put("wifi_num_open_networks_kept",null);
        systemSettingsMap.put("screen_brightness",String.valueOf(random.nextInt(Integer.MAX_VALUE)));
        systemSettingsMap.put("use_google_mail",null);
        systemSettingsMap.put("vibrate_on",null);
        systemSettingsMap.put("wifi_watchdog_acceptable_packet_loss_percentage",null);
        systemSettingsMap.put("min_refresh_rate",null);
        systemSettingsMap.put("bluetooth",null);
        systemSettingsMap.put("SIP_ADDRESS_ONLY",null);
        systemSettingsMap.put("notification_vibration_intensity",null);
        systemSettingsMap.put("haptic_feedback_enabled",String.valueOf(random.nextBoolean()?0:1));//0
        systemSettingsMap.put("desk_dock_sound",getRandomString(10));//"/product/media/audio/ui/Dock.ogg");
        systemSettingsMap.put("setup_wizard_has_run",null);
        systemSettingsMap.put("window_orientation_listener_log",null);
        systemSettingsMap.put("_last_audible",null);
        systemSettingsMap.put("animator_duration_scale",String.valueOf(1.0 + randomSmallDouble(1.0)));
    }
}
