package DAO;

import bean.CategoryBook;

/**
 * Created by dream on 17-5-1.
 */
public class CategoryBookDAO extends CommonDAO<CategoryBook> {

    private static final String tableName = "CategoryBooks";

    private static final CategoryBookDAO categoryBookDAO = new CategoryBookDAO();

    private CategoryBookDAO() {
    }

    static public CategoryBookDAO getInstance() {
        return categoryBookDAO;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void createTable(CategoryBook bean) {

    }

}
