package io.github.CoreLesser.screen.settings

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.VisScrollPane
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.VisTextButton
import io.github.CoreLesser.enumclass.Language
import io.github.CoreLesser.manager.FontManager
import io.github.CoreLesser.manager.I18NManager
import ktx.app.KtxGame
import ktx.app.KtxScreen

class LanguageOptionsMenuScreen(
    private val game: KtxGame<KtxScreen>
) : KtxScreen {
    // 定义一个舞台
    private val stage : Stage = Stage(ExtendViewport(1280f, 720f))
    // 定义一个根table用于布局
    private val rootTable : VisTable = VisTable()
    // 定义一个table容纳UI
    private val contentTable : VisTable = VisTable()
    // 定义一个滑动容器
    private val contentScroll : VisScrollPane = VisScrollPane(contentTable)
    // 修改按钮样式
    private val skin = VisUI.getSkin().apply {
        get(VisTextButton.VisTextButtonStyle::class.java).font = FontManager.loadAllFont(24)
    }
    // 定义选项
    private val options = listOf(
        VisTextButton("zh_CN").apply {
            addListener(object : ClickListener() {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    I18NManager.setLanguage(Language.SIMPLE_CHINESE)
                    game.addScreen(SettingsMenuScreen(game))
                    game.setScreen<SettingsMenuScreen>()
                }
            })
        }
    )
    // 选项信息索引
    private val optionsTextKey = listOf(
        "zh_CN"
    )

    // 初始化语言选项菜单
    init {
        options.forEach {
            it.skin = skin
        }
        createLanguageOptionsMenu()
    }

    private fun createLanguageOptionsMenu() {
        // 定义根表格内容
        rootTable.apply {
            setFillParent(true)
            center()
            defaults().pad(10f)
            add(contentScroll).width(1280f).height(720f)
        }
        stage.addActor(rootTable)
        // 将语言选项添加到容器
        contentTable.apply {
            for  (i in options.indices) {
                options[i].setText(I18NManager.getString(optionsTextKey[i]))
                contentTable.add(options[i]).width(400f).height(40f).row()
            }
        }
    }

    // 设置输入为舞台
    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    // 清屏并设置背景颜色、渲染舞台
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(delta)
        stage.draw()
    }

    // 更新屏幕大小时更新舞台大小
    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width,height,true)
    }

    // 释放舞台
    override fun dispose() {
        stage.dispose()
    }
}
