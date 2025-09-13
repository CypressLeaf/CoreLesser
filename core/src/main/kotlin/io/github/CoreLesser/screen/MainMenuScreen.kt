package io.github.CoreLesser.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.VisTextButton
import io.github.CoreLesser.manager.FontManager
import io.github.CoreLesser.manager.I18NManager
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
    /***
     * 战役按钮
     * 多人按钮
     * 教程按钮
     * 设置按钮
     * 退出按钮
     */
    private val buttons = listOf(
        VisTextButton(""),
        VisTextButton(""),
        VisTextButton(""),
        VisTextButton(""),
        VisTextButton("")
    )

    // 初始化主菜单
    init {
        buttons.forEach { it.skin = FontManager.getSkin() }
        createMainMenu()
    }

    // 构建主菜单
    private fun createMainMenu() {
        rootTable.apply {
            setFillParent(true)
            bottom().left()
            defaults().pad(10f)
            padBottom(20f)
            padLeft(40f)// 添加所有按钮
            buttons.forEach { button ->
                add(button).width(400f).height(40f).row()
            }
        }
        buttons[0].setText(I18NManager.getString("战役"))
        buttons[1].setText(I18NManager.getString("游戏"))
        buttons[2].setText(I18NManager.getString("教程"))
        buttons[3].setText(I18NManager.getString("设置"))
        buttons[4].setText(I18NManager.getString("退出"))
        buttons[4].apply {
            addListener(object : ClickListener() {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    Gdx.app.exit()
                }
            })
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
