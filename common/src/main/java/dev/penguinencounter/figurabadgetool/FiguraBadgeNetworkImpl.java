package dev.penguinencounter.figurabadgetool;

import dev.penguinencounter.figurabadgetool.ducks.FiguraHttpAPIExt;
import dev.penguinencounter.figurabadgetool.mixin.FiguraNetworkAccessor;
import net.minecraft.network.chat.Component;
import org.figuramc.figura.gui.FiguraToast;

import static net.minecraft.Util.NIL_UUID;

public class FiguraBadgeNetworkImpl {
    public static void setBadge(int id) {
        FiguraNetworkAccessor.invokeQueueString(
                NIL_UUID,
                api -> ((FiguraHttpAPIExt) api).figuraBadgeTool$setBadge(id),
                (code, data) -> {
                    if (code != 200) {
                        FiguraToast.sendToast(
                                Component.translatableWithFallback(
                                        "figura.backend.badge_set_error",
                                        "Failed to set badge"
                                ),
                                FiguraToast.ToastType.ERROR
                        );
                        return;
                    }
                    FiguraToast.sendToast(
                            Component.translatableWithFallback("figura.backend.badge_set", "Badge set successfully!")
                    );
                }
        );
    }

    public static void clearBadge() {
        FiguraNetworkAccessor.invokeQueueString(
                NIL_UUID,
                api -> ((FiguraHttpAPIExt) api).figuraBadgeTool$clearBadge(),
                (code, data) -> {
                    if (code != 200) {
                        FiguraToast.sendToast(
                                Component.translatableWithFallback(
                                        "figura.backend.badge_clear_error",
                                        "Failed to clear badge"
                                ),
                                FiguraToast.ToastType.ERROR
                        );
                        return;
                    }
                    FiguraToast.sendToast(
                            Component.translatableWithFallback(
                                    "figura.backend.badge_clear",
                                    "Badge cleared successfully!"
                            )
                    );
                }
        );
    }
}
