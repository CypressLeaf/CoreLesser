package io.github.CoreLesser.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.utils.Disposable
import io.github.CoreLesser.enumclass.Language

// 字符管理器
object FontManager : Disposable {
    // 加载中文
    fun loadChinese() : BitmapFont{
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/03TogeGothic-SemiLight-2"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().apply {
            size = 64
            mono = false
            incremental = true
        }
        return generator.generateFont(parameter)
    }

    // 通过语言枚举加载字符
    fun loadFontByLanguage(language : Language) : BitmapFont {
        return when (language) {
            Language.SIMPLE_CHINESE -> loadChinese()
            else -> loadChinese()
        }
    }

    // 释放资源
    override fun dispose() {}
}
