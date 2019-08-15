import java.util.List;

import com.vaadin.root.dao.DefaultDao;
import com.vaadin.root.dao.ItemCustomizationDao;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.model.ItemCustomization;

public class ItemCustomizationDaoImpl extends DefaultDao implements ItemCustomizationDao{

	@Override
	public List<ItemCustomization> findAll() {
		// TODO Auto-generated method stub
		return getEntityManager().createNamedQuery("ItemCustomization.findAll",ItemCustomization.class).getResultList();

	}

}
