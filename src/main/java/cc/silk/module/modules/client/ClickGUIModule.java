package cc.silk.module.modules.client;

import cc.silk.gui.newgui.NewClickGUI;
import cc.silk.module.Category;
import cc.silk.module.Module;
import cc.silk.module.setting.ModeSetting;
import cc.silk.gui.theme.ThemeManager;
import org.lwjgl.glfw.GLFW;

public final class ClickGUIModule extends Module {

    public static final ModeSetting theme = new ModeSetting(
            "Theme",
            ThemeManager.getDefaultName(),
            ThemeManager.getThemeNamesArray()
    );

    public ClickGUIModule() {
        super("Click Gui", "Toggles the Silk GUI", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.CLIENT);
        addSettings(theme);
    }

    @Override
    public void onEnable() {
        if (mc == null) {
            return;
        }

        // Mở gui
        mc.setScreen(new NewClickGUI());

        // KHÔNG tắt module ngay sau mở
        // Nếu bạn muốn auto disable thì thêm vào ModuleManager để nó không ảnh hưởng GUI
    }

    @Override
    public void onDisable() {
        // Nếu tắt GUI khi module disable thì thêm cái này vào
        if (mc != null && mc.currentScreen instanceof NewClickGUI) {
            mc.setScreen(null);
        }
    }

    @Override
    public void onSettingChange() {
        // cập nhật theme ngay khi đổi
        ThemeManager.setTheme(theme.getValue());
    }
}
