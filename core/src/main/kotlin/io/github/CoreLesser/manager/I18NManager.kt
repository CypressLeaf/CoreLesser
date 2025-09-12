package io.github.CoreLesser.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.I18NBundle
import io.github.CoreLesser.enumclass.Language
import java.util.Locale

object I18NManager : Disposable {
    //启用i18n
    private var currentBundle : I18NBundle
    //当前语言
    private var currentLanguage : Language

    //初始化语言管理器（默认中文简体）
    init {
        // 设置默认语言
        currentLanguage = Language.SIMPLE_CHINESE
        // 加载默认的语言资源包
        currentBundle = I18NBundle.createBundle(
            Gdx.files.internal("i18n/language"),
            Locale.SIMPLIFIED_CHINESE
        )
    }

    // 设置当前语言（通过code设置）
    fun setLanguageByCode(code : String) {
        val language = Language.formCode(code)
        setLanguage(language)
    }

    // 设置当前语言（通过displayName设置）
    fun setLanguageByDisplayName(displayName : String) {
        val language = Language.formDisplayName(displayName)
        setLanguage(language)
    }

    // 设置当前语言（通过locale设置）
    fun setLanguageByLocale(locale: Locale) {
        val locale = Language.formLocale(locale)
        setLanguage(locale)
    }

    // 设置当前语言（通过语言枚举设置）
    fun setLanguage(language : Language) {
        if (currentLanguage != language) {
            currentLanguage = language
            val locale = when (language) {
                Language.SIMPLE_CHINESE -> Locale.SIMPLIFIED_CHINESE
            }
            currentBundle = I18NBundle.createBundle(
                Gdx.files.internal("i18n/language"),
                locale
            )
        }
    }

    // 获取本地化文本
    fun getString(key : String) : String{
        // key：文本的标记（文本键）
        return try {
            currentBundle.get(key)
        } catch (e : Exception) {
            Gdx.app.error("I18N管理器","I18N管理器找不到对应的本地化标记。")
            "!$key!"
        }
    }

    // 释放资源
    override fun dispose() {}
}
