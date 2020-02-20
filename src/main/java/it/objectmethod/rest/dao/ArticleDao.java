package it.objectmethod.rest.dao;

import java.util.List;

import it.objectmethod.rest.model.Article;

public interface ArticleDao {

	public List<Article> getArticles();

	public int insert(Article article);

	public int update(Article article);

	public Article getArticleByCode(String codArt);
}
