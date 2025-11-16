package controller;

import dao.CardapioDAO;
import model.ItemCardapio;

import java.util.List;

public class CardapioController {
    private CardapioDAO cardapioDAO = new CardapioDAO();

    public void salvar(ItemCardapio item) {
        cardapioDAO.salvar(item);
    }

    public List<ItemCardapio> listar() {
        return cardapioDAO.listar();
    }

    public void excluir(int id) {
        cardapioDAO.excluir(id);
    }
}
