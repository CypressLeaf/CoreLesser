package io.github.CoreLesser.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.VisTextButton
import io.github.CoreLesser.enumclass.Language

object GameSkin : Skin() {
    // 定义皮肤
    val skin : Skin
        get() = VisUI.getSkin()

    // 皮肤加载
    fun load(language: Language) {
        updateFontInSkin(VisUI.getSkin(),generatorFontFormLanguage(language))
    }

    fun updateFontInSkin(skin: Skin,font: BitmapFont) {
        skin.get(VisTextButton.VisTextButtonStyle::class.java).font = font
    }

    // 根据语言构造字体
    fun generatorFontFormLanguage(language: Language) : BitmapFont {
        val fonFiles = when (language) {
            Language.SIMPLE_CHINESE -> "fonts/03TogeGothic-SemiLight-2.otf"
        }
        val generator = FreeTypeFontGenerator(Gdx.files.internal(fonFiles))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().apply {
            size = 64
            mono = false
            incremental = true
        }
        return generator.generateFont(parameter)
    }

    override fun dispose() {
        skin.dispose()
    }
}
