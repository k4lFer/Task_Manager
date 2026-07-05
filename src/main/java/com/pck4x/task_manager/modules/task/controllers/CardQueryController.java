package com.pck4x.task_manager.modules.task.controllers;

import com.pck4x.task_manager.modules.task.objects.dtos.query.CardDetailDto;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CardSummaryDto;
import com.pck4x.task_manager.modules.task.use_cases.query.GetCardByIdQuery;
import com.pck4x.task_manager.modules.task.use_cases.query.GetCardsByListQuery;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/card")
@Tag(name = "Cards")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class CardQueryController {
    private final GetCardsByListQuery getCardsByListQuery;
    private final GetCardByIdQuery getCardByIdQuery;

    @GetMapping("/list/{listId}/cards")
    @Operation(summary = "Get cards by list", description = "Retrieves all cards in a specific list/column.")
    @ApiResponse(responseCode = "200", description = "List of cards", content = @Content(schema = @Schema(implementation = CardSummaryDto.class)))
    public ResponseEntity<?> GetCardsByList(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID listId
    ) {
        var result = getCardsByListQuery.execute(listId, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }

    @GetMapping("/{cardId}")
    @Operation(summary = "Get card details", description = "Retrieves detailed information about a specific card including members and labels.")
    @ApiResponse(responseCode = "200", description = "Card details", content = @Content(schema = @Schema(implementation = CardDetailDto.class)))
    public ResponseEntity<?> GetById(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID cardId
    ) {
        var result = getCardByIdQuery.execute(cardId, UUID.fromString(userId));
        return ResponseHelper.toResponse(result);
    }
}
