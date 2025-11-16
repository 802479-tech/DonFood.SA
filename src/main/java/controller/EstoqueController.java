package controller;

import dao.EstoqueDAO;
import model.ItemEstoque;

import java.util.List;

public class EstoqueController {
    private EstoqueDAO estoqueDAO = new EstoqueDAO();

    public void salvar(ItemEstoque item) {
        estoqueDAO.salvar(item);
    }

    public List<ItemEstoque> listar() {
        return estoqueDAO.listar();
    }

    public void excluir(int id) {
        estoqueDAO.excluir(id);
    }
}
