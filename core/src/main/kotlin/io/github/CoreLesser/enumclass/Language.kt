package io.github.CoreLesser.enumclass

import java.util.Locale

//枚举可选语言
enum class Language(
    val code : String,
    val displayName : String,
    val locale: Locale
) {
    SIMPLE_CHINESE("zh_CN","简体中文", Locale.SIMPLIFIED_CHINESE);

    /***
     * 根据语言代码获取对应的Language枚举
     * code：语言代码（如zh_CN）
     * 找不到返回简体中文
     */
    companion object {
        fun formCode(code : String) : Language {
            return values().firstOrNull { it.code == code } ?: Language.SIMPLE_CHINESE
        }
        fun formDisplayName(displayName : String) : Language {
            return values().firstOrNull { it.displayName == displayName } ?: Language.SIMPLE_CHINESE
        }
        fun formLocale(locale : Locale) : Language {
            return values().firstOrNull { it.locale == locale } ?: Language.SIMPLE_CHINESE
        }
    }
}
