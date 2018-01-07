package dao;

import model.News;

import java.io.Serializable;
import java.util.List;

public interface NewsDAO extends GenericDAO <News, Long>{



    public List<News> getAll();
}
