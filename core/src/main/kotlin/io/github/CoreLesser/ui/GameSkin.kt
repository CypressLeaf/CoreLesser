package io.github.CoreLesser.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.VisTextButton
import io.github.CoreLesser.enumclass.Language

object GameSkin {
    // 定义皮肤
    val skin : Skin
        get() = VisUI.getSkin()

    // 初始化皮肤
    init {
        load(Language.SIMPLE_CHINESE)
    }

    // 皮肤加载
    fun load(language: Language) {
        // 更新皮肤字符集
        updateFontInSkin(VisUI.getSkin(),generatorFontFormLanguage(language))
        // 加载图集
        val atlas = TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"))
        skin.addRegions(atlas)
        // 生成字体并添加到皮肤
        val font = generatorFontFormLanguage(language)
        skin.add("default-font", font)
        // 加载皮肤定义
        skin.load(Gdx.files.internal("ui/uiskin.json"))
    }

    fun updateFontInSkin(skin: Skin,font: BitmapFont) {
        val buttonStyle = skin.get(VisTextButton.VisTextButtonStyle::class.java)
        buttonStyle.font = font
        skin.add("default",buttonStyle)
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

    fun dispose() {
        skin.dispose()
    }
}
