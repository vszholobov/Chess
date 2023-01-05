package com.testirovanie.lab4.config;

import com.testirovanie.lab4.model.board.ChessBoard;
import com.testirovanie.lab4.model.figure.FigureType;
import com.testirovanie.lab4.service.MoveValidationService;
import com.testirovanie.lab4.validation.MoveValidator;
import com.testirovanie.lab4.validation.MoveValidatorChain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class MoveConfig {
    private final ChessBoard chessboard;

    @Bean
    public MoveValidationService moveValidationService(List<MoveValidatorChain> moveValidatorChains) {
        Map<FigureType, MoveValidatorChain> moveValidatorChainMap = new HashMap<>();
        for (MoveValidatorChain moveValidatorChain : moveValidatorChains) {
            moveValidatorChainMap.put(moveValidatorChain.getFigureType(), moveValidatorChain);
        }
        return new MoveValidationService(moveValidatorChainMap);
    }

    @Bean
    public MoveValidator boardersBreachValidator() {
        return tChessMove -> {
            boolean valid = tChessMove.destination().getCol() >= 1 &&
                    tChessMove.destination().getCol() <= 8 &&
                    tChessMove.destination().getRow() >= 1 &&
                    tChessMove.destination().getRow() <= 8;
            if (!valid) {
                throw new RuntimeException("boardersBreachValidator");
            }
        };
    }

    @Bean
    public MoveValidator startEqualsDestValidator() {
        return tChessMove -> {
            boolean isValid = tChessMove.departure() != tChessMove.destination();
            if (!isValid) {
                throw new RuntimeException("startEqualsDestValidator");
            }
        };
    }

    @Bean
    public MoveValidator notAllyChessmanValidator() {
        return tChessMove -> {
            boolean isValid = !(tChessMove.destination().isBusy() &&
                    tChessMove.figure().side() ==
                            tChessMove.destination().getFigure().side());
            if (!isValid) {
                throw new RuntimeException("notAllyChessmanValidator");
            }
        };
    }

    @Bean
    public MoveValidator linePathValidator() {
        return tChessMove -> {

            int startCol = tChessMove.departure().getCol();
            int startRow = tChessMove.departure().getRow();

            int destCol = tChessMove.destination().getCol();
            int destRow = tChessMove.destination().getRow();

            if (destRow != startRow && destCol != startCol) {
                return;
            }

            int temp = startCol;
            startCol = Math.min(startCol, destCol);
            destCol = Math.max(temp, destCol);

            int tempRow = startRow;
            startRow = Math.min(startRow, destRow);
            destRow = Math.max(tempRow, destRow);

            if (destRow == startRow) {
                for (int i = startCol + 1; i < destCol; i++) {
                    if (chessboard.getField(destRow, i).isBusy()) {
                        throw new RuntimeException("linePathValidator");
                    }
                }
            } else {
                for (int i = startRow + 1; i < destRow; i++) {

                    if (chessboard.getField(i, destCol).isBusy()) {
                        throw new RuntimeException("linePathValidator");
                    }
                }
            }
        };
    }

    @Bean
    public MoveValidator diagonalPathValidator() {
        return tChessMove -> {
            int startCol = tChessMove.departure().getCol();
            int startRow = tChessMove.departure().getRow();

            int destCol = tChessMove.destination().getCol();
            int destRow = tChessMove.destination().getRow();

            if (Math.abs(destRow - startRow) != Math.abs(destCol - startCol)) {
                return;
            }

            if ((destCol > startCol && destRow < startRow) || (destCol < startCol && destRow > startRow)) {
                for (int i = startCol; i < destCol; i++) {

                    if (chessboard.getField(i, 8 - i).isBusy()) {
                        throw new RuntimeException("diagonalPathValidator");
                    }
                }
            } else {
                for (int i = startCol; i < destCol; i++) {

                    if (chessboard.getField(i, i).isBusy()) {
                        throw new RuntimeException("diagonalPathValidator");
                    }
                }
            }
        };
    }

    @Bean
    public MoveValidator knightMoveValidator() {
        return tChessMove -> {
            int startCol = tChessMove.departure().getCol();
            int startRow = tChessMove.departure().getRow();

            int destCol = tChessMove.destination().getCol();
            int destRow = tChessMove.destination().getRow();

            boolean isValid = (Math.abs(destCol - startCol) == 2 && Math.abs(destRow - startRow) == 1) ||
                    (Math.abs(destCol - startCol) == 1 && Math.abs(destRow - startRow) == 2);
            if (!isValid) {
                throw new RuntimeException("knightMoveValidator");
            }
        };
    }

    @Bean
    public MoveValidator queenMoveValidator() {
        return tChessMove -> {
            int startCol = tChessMove.departure().getCol();
            int startRow = tChessMove.departure().getRow();

            int destCol = tChessMove.destination().getCol();
            int destRow = tChessMove.destination().getRow();

            if (destRow == startRow || destCol == startCol) {
                return;
            }

            if (Math.abs(destRow - startRow) == Math.abs(destCol - startCol)) {
                return;
            }

            throw new RuntimeException("queenMoveValidator");
        };
    }

    @Bean
    public MoveValidator rookMoveValidator() {
        return tChessMove -> {
            int startCol = tChessMove.departure().getCol();
            int startRow = tChessMove.departure().getRow();

            int destCol = tChessMove.destination().getCol();
            int destRow = tChessMove.destination().getRow();

            boolean isValid = destRow == startRow || destCol == startCol;
            if (!isValid) {
                throw new RuntimeException("rookMoveValidator");
            }
        };
    }

    @Bean
    public MoveValidatorChain knightValidators() {
        return new MoveValidatorChain(List.of(boardersBreachValidator(),
                startEqualsDestValidator(),
                notAllyChessmanValidator(),
                knightMoveValidator()), FigureType.KNIGHT);
    }

    @Bean
    public MoveValidatorChain queenValidators() {
        return new MoveValidatorChain(List.of(boardersBreachValidator(),
                startEqualsDestValidator(),
                notAllyChessmanValidator(),
                queenMoveValidator(),
                linePathValidator(),
                diagonalPathValidator()), FigureType.QUEEN);
    }

    @Bean
    public MoveValidatorChain rookValidators() {
        return new MoveValidatorChain(List.of(boardersBreachValidator(),
                startEqualsDestValidator(),
                notAllyChessmanValidator(),
                rookMoveValidator(),
                linePathValidator()), FigureType.ROOK);
    }
}
