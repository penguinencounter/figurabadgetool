package dev.penguinencounter.figurabadgetool.mixin;

import dev.penguinencounter.figurabadgetool.ducks.FiguraHttpAPIExt;
import org.figuramc.figura.backend2.HttpAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.net.http.HttpRequest;

@Mixin(value = HttpAPI.class, remap = false)
public abstract class FiguraHttpAPIMixin implements FiguraHttpAPIExt {
    @Shadow
    abstract protected HttpRequest.Builder header(String url);

    @Unique
    @Override
    public HttpRequest figuraBadgeTool$setBadge(Integer badgeId) {
        String badge = badgeId.toString();
        return header("temp_badges").POST(HttpRequest.BodyPublishers.ofString(badge))
                .header("Content-Type", "application/json")
                .build();
    }
}
