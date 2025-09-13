package io.github.CoreLesser.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.VisTextButton
import io.github.CoreLesser.manager.FontManager
import io.github.CoreLesser.manager.I18NManager
import io.github.CoreLesser.screen.settings.SettingsMenuScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.collections.GdxArray
import ktx.freetype.freeTypeFontParameters

class MainMenuScreen(
    private val game: KtxGame<KtxScreen>
) : KtxScreen {
    // 定义一个舞台
    private val stage : Stage = Stage(ExtendViewport(1280f,720f))
    // 定义一个table容纳UI
    private val rootTable : VisTable = VisTable()
    // 修改按钮样式
    private val skin = VisUI.getSkin().apply {
        get(VisTextButton.VisTextButtonStyle::class.java).font = FontManager.loadAllFont(48)
    }
    /***
     * 战役按钮
     * 联机按钮
     * 教程按钮
     * 模组按钮
     * 设置按钮
     * 退出按钮
     */
    private val buttons = listOf(
        VisTextButton("战役"),
        VisTextButton("联机"),
        VisTextButton("教程"),
        VisTextButton("模组"),
        VisTextButton("设置").apply {
            addListener(object : ClickListener() {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    game.addScreen(SettingsMenuScreen(game))
                    game.setScreen<SettingsMenuScreen>()
                }
            })
        },
        VisTextButton("退出").apply {
            addListener(object : ClickListener() {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    Gdx.app.exit()
                }
            })
        }
    )
    // 按钮信息索引
    private val buttonTextKeys = listOf("战役", "联机", "教程", "模组", "设置", "退出")

    // 初始化主菜单
    init {
        buttons.forEach {
            it.skin = skin
        }
        // 构建主菜单
        createMainMenu()
    }

    // 构建主菜单
    private fun createMainMenu() {
        // 定义根表格内容
        rootTable.apply {
            setFillParent(true)
            left()
            defaults().pad(20f)
            padLeft(40f)
            // 添加所有按钮
            for (i in buttons.indices) {
                // 根据索引找到自己的文本并添加按钮
                buttons[i].setText(I18NManager.getString(buttonTextKeys[i]))
                rootTable.add(buttons[i]).width(200f).height(60f).row()
            }
        }
        stage.addActor(rootTable)
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
