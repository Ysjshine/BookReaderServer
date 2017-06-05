package DAO;

import bean.CategoryBook;
import org.jetbrains.annotations.NotNull;

/**
 * Created by dream on 17-5-1.
 */
public class CategoryBookDAO extends CommonDAO<CategoryBook> {

    private static final String tableName = "CategoryBooks";

    private static final CategoryBookDAO categoryBookDAO = new CategoryBookDAO();

    private CategoryBookDAO() {
    }

    @NotNull
    static public CategoryBookDAO getInstance() {
        return categoryBookDAO;
    }

    @NotNull
    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void createTable(CategoryBook bean) {

    }

}
