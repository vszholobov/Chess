package com.testirovanie.lab4.config;

import com.testirovanie.lab4.board.ChessBoard;
import com.testirovanie.lab4.board.move.MoveFactory;
import com.testirovanie.lab4.figure.Figure;
import com.testirovanie.lab4.figure.FigureProperties;
import com.testirovanie.lab4.figure.FigureType;
import com.testirovanie.lab4.figure.FigureSide;
import com.testirovanie.lab4.board.move.validation.MoveValidator;
import com.testirovanie.lab4.board.move.validation.MoveValidatorChain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class FigureConfig {

    private final ChessBoard chessboard;
    private final MoveFactory moveFactory;

    @Bean
    public MoveValidator boardersBreachValidator() {
        return tChessMove -> {
            boolean valid = tChessMove.toField().getCol() >= 1 &&
                    tChessMove.toField().getCol() <= 8 &&
                    tChessMove.toField().getRow() >= 1 &&
                    tChessMove.toField().getRow() <= 8;
            if (!valid) {
                throw new RuntimeException("boardersBreachValidator");
            }
        };
    }

    @Bean
    public MoveValidator startEqualsDestValidator() {
        return tChessMove -> {
            boolean isValid = tChessMove.figureProperties().field() != tChessMove.toField();
            if (!isValid) {
                throw new RuntimeException("startEqualsDestValidator");
            }
        };
    }

    @Bean
    public MoveValidator notAllyChessmanValidator() {
        return tChessMove -> {
            boolean isValid = !(tChessMove.toField().isBusy() &&
                    tChessMove.figureProperties().side() ==
                            tChessMove.toField().getFigure().getProperties().side());
            if (!isValid) {
                throw new RuntimeException("notAllyChessmanValidator");
            }
        };
    }

    @Bean
    public MoveValidator aliveValidator() {
        return tChessMove -> {
            boolean isValid = !tChessMove.figureProperties().isDead();
            if (!isValid) {
                throw new RuntimeException("aliveValidator");
            }
        };
    }

    @Bean
    public MoveValidator linePathValidator() {
        return tChessMove -> {

            int startCol = tChessMove.figureProperties().field().getCol();
            int startRow = tChessMove.figureProperties().field().getRow();

            int destCol = tChessMove.toField().getCol();
            int destRow = tChessMove.toField().getRow();

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
                for (int i = startCol + 1; i < destCol - 1; i++) {
                    if (chessboard.getField(destRow, i).isBusy()) {
                        throw new RuntimeException("linePathValidator");
                    }
                }
            } else {
                for (int i = startRow + 1; i < destRow - 1; i++) {

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
            int startCol = tChessMove.figureProperties().field().getCol();
            int startRow = tChessMove.figureProperties().field().getRow();

            int destCol = tChessMove.toField().getCol();
            int destRow = tChessMove.toField().getRow();

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
            int startCol = tChessMove.figureProperties().field().getCol();
            int startRow = tChessMove.figureProperties().field().getRow();

            int destCol = tChessMove.toField().getCol();
            int destRow = tChessMove.toField().getRow();

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
            int startCol = tChessMove.figureProperties().field().getCol();
            int startRow = tChessMove.figureProperties().field().getRow();

            int destCol = tChessMove.toField().getCol();
            int destRow = tChessMove.toField().getRow();

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
            int startCol = tChessMove.figureProperties().field().getCol();
            int startRow = tChessMove.figureProperties().field().getRow();

            int destCol = tChessMove.toField().getCol();
            int destRow = tChessMove.toField().getRow();

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
                aliveValidator(),
                knightMoveValidator()));
    }

    @Bean
    public MoveValidatorChain queenValidators() {
        return new MoveValidatorChain(List.of(boardersBreachValidator(),
                startEqualsDestValidator(),
                notAllyChessmanValidator(),
                aliveValidator(),
                queenMoveValidator(),
                linePathValidator(),
                diagonalPathValidator()));
    }

    @Bean
    public MoveValidatorChain rookValidators() {
        return new MoveValidatorChain(List.of(boardersBreachValidator(),
                startEqualsDestValidator(),
                notAllyChessmanValidator(),
                aliveValidator(),
                rookMoveValidator(),
                linePathValidator()));
    }

    @Bean
    public FigureProperties whiteLeftKnightProperties(@Value("${knight.row}") Integer row, @Value("${knight.col}") Integer col) {
        return new FigureProperties(FigureType.KNIGHT, chessboard.getField(row, col), FigureSide.WHITE);
    }

    @Bean
    public FigureProperties whiteRightRookProperties() {
        return new FigureProperties(FigureType.ROOK, chessboard.getField(1, 8), FigureSide.WHITE);
    }

    @Bean
    public FigureProperties blackQueenProperties() {
        return new FigureProperties(FigureType.QUEEN, chessboard.getField(8, 4), FigureSide.BLACK);
    }

    @Bean
    @Profile({"Pasha", "Seva"})
    public Figure whiteKnight(@Qualifier("whiteLeftKnightProperties") FigureProperties whiteLeftKnightProperties) {
        return new Figure(whiteLeftKnightProperties, knightValidators(), moveFactory);
    }

    @Bean
    @Profile("Pasha")
    public Figure whiteRightRook() {
        return new Figure(whiteRightRookProperties(), rookValidators(), moveFactory);
    }

    @Bean
    @Profile("Seva")
    public Figure blackQueen() {
        return new Figure(blackQueenProperties(), queenValidators(), moveFactory);
    }
}
