package hcmute.edu.vn.mssv18110273.appbanhang.database;

import hcmute.edu.vn.mssv18110273.appbanhang.dao.BanHangDAO;
import hcmute.edu.vn.mssv18110273.appbanhang.model.CategoryModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemModel;

public class ResourceData
{

    public static void insertCategory(BanHangDAO banHangDAO) {
        CategoryModel categoryModel1 = new CategoryModel();
        categoryModel1.setName("Laptop HP");
        categoryModel1.setDescription("Danh mục các loại laptop của hãng HP");
        categoryModel1.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/HP_logo_2012.svg/600px-HP_logo_2012.svg.png");

        CategoryModel categoryModel2 = new CategoryModel();
        categoryModel2.setName("Laptop Dell");
        categoryModel2.setDescription("Danh mục các loại laptop của hãng DELL");
        categoryModel2.setImageUrl("https://brasol.vn/public/ckeditor/uploads/thiet-ke-logo-tin-tuc/logo-dell-png.png");

        CategoryModel categoryModel3 = new CategoryModel();
        categoryModel3.setName("Laptop Acer");
        categoryModel3.setDescription("Danh mục các loại laptop của hãng Acer");
        categoryModel3.setImageUrl("https://thunknews.com/wp-content/uploads/2014/07/acer-logo.jpg");

        CategoryModel categoryModel4 = new CategoryModel();
        categoryModel4.setName("Laptop ASUS");
        categoryModel4.setDescription("Danh mục các loại laptop của hãng ASUS");
        categoryModel4.setImageUrl("https://phucgia.com.vn/wp-content/uploads/2020/03/logo-Asus.jpg");

        CategoryModel categoryModel5 = new CategoryModel();
        categoryModel5.setName("Laptop Lenovo");
        categoryModel5.setDescription("Danh mục các loại laptop của hãng LENOVO");
        categoryModel5.setImageUrl("https://phucgia.com.vn/wp-content/uploads/2020/03/Lenovo-Logo.jpg");

        banHangDAO.insertCategory(categoryModel1);
        banHangDAO.insertCategory(categoryModel2);
        banHangDAO.insertCategory(categoryModel3);
        banHangDAO.insertCategory(categoryModel4);
        banHangDAO.insertCategory(categoryModel5);
    }

    public static void insertItem(BanHangDAO banHangDAO) {
        //HP
        ItemModel itemModel1 = new ItemModel();
        itemModel1.setCategoryId(1L);
        itemModel1.setName("Laptop HP Envy 13 ba1028TU i5 1135G7/8GB/512GB/Win 10");
        itemModel1.setPrice(22790000L);
        itemModel1.setQuantity(50L);
        itemModel1.setDescription("Mua laptop HP Envy 13 ba1028TU i5 trang bị intel thế hệ 11 mới nhất, mạnh mẽ hơn, thiết kế tối giản vô cùng đẹp, hỗ trợ trả góp 0%, bảo hành chính hãng, giao hàng toàn quốc");
        itemModel1.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/3/19/637517684143067081_hp-envy-13-ba-vang-dd-icon.jpg");// phai link http ms dc

        ItemModel itemModel2 = new ItemModel();
        itemModel2.setCategoryId(1L);
        itemModel2.setName("Laptop HP 348 G7 i5 10210U/8GB/512GB SSD/WIN10");
        itemModel2.setPrice(21390000L);
        itemModel2.setQuantity(10L);
        itemModel2.setDescription("Mua laptop HP 348 G7 i5 10210U trang bị core i5 thế hệ 10 mới nhất, đặc biệt hơn khi FPTShop hỗ trợ mua trả góp 0% thủ tục xét duyệt nhanh, giao hàng chỉ trong 1h. MUA NGAY!");
        itemModel2.setImageUrl("https://cdn.nguyenkimmall.com/images/thumbnails/600/336/detailed/715/10048720-laptop-dell-inspiron-3501-i5-1135g7-15-6-inch-70234074-1.jpg");

        ItemModel itemModel3 = new ItemModel();
        itemModel3.setCategoryId(1L);
        itemModel3.setName("Laptop HP Pavilion Gaming 15 dk1086TX i7 10750H/8GB/512GB/GF 1650Ti4GB/15.6FHD/Win 10");
        itemModel3.setPrice(27990000L);
        itemModel3.setQuantity(50L);
        itemModel3.setDescription("Mua laptop HP 348 G7 i5 10210U trang bị core i5 thế hệ 10 mới nhất, đặc biệt hơn khi FPTShop hỗ trợ mua trả góp 0% thủ tục xét duyệt nhanh, giao hàng chỉ trong 1h. MUA NGAY!\"");
        itemModel3.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2020/12/7/637429525559071095_hp-pavilion-gaming-15-dk1086tx-dd.png");

        banHangDAO.insertItem(itemModel1);
        banHangDAO.insertItem(itemModel2);
        banHangDAO.insertItem(itemModel3);

        //DELL
        ItemModel itemModel4 = new ItemModel();
        itemModel4.setCategoryId(2L);
        itemModel4.setName("Laptop Dell Inspiron N3501 i5 1135G7/8GB/512GB/NV GeForce MX330 2GB/15.6 FHD/Win 10");
        itemModel4.setPrice(19090000L);
        itemModel4.setQuantity(20L);
        itemModel4.setDescription("Mua laptop Dell Inspiron N3501 i5 hiệu năng vượt trội, hỗ trợ trả góp 0% xét duyệt cực nhanh, miễn phí giao hàng tận nhà, bảo hành chính hãng 1 năm");
        itemModel4.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2020/12/12/637433621198303843_dell-inspiron-n3501-den-dd.png");// phai link http ms dc

        ItemModel itemModel5 = new ItemModel();
        itemModel5.setCategoryId(2L);
        itemModel5.setName("Laptop Dell Vostro V3500 i5 1135G7/8GB/256GB/15.6 FHD/Win 10");
        itemModel5.setPrice(17690000L);
        itemModel5.setQuantity(10L);
        itemModel5.setDescription("Mua ngay, Miễn phí vận chuyển");
        itemModel5.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/2/22/637495973910034211_dell-vostro-v3500-den-dd.jpg");

        ItemModel itemModel6 = new ItemModel();
        itemModel6.setCategoryId(2L);
        itemModel6.setName("Laptop Dell G3 15 i5 10300H/2x4GB/256GB+1TB/GTX 1650 4GB/15.6 FHD/Win 10 ");
        itemModel6.setPrice(10000000L);
        itemModel6.setQuantity(12L);
        itemModel6.setDescription("Hỗ trợ trả góp 0% kèm chương trình giảm giá đang hấp dẫn ✅ Miễn phí giao hàng tận nơi ✅ Bảo hành toàn diện 1 năm chính hãngHỗ trợ trả góp 0% kèm chương trình giảm giá đang hấp dẫn, Miễn phí giao hàng tận nơi, Bảo hành toàn diện 1 năm chính hãng");
        itemModel6.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2019/10/30/637080523484151384_dell-g3-15-3590-den-dd.png");

        banHangDAO.insertItem(itemModel4);
        banHangDAO.insertItem(itemModel5);
        banHangDAO.insertItem(itemModel6);

        // ACER
        ItemModel itemModel7 = new ItemModel();
        itemModel7.setCategoryId(3L);
        itemModel7.setName("Laptop Acer Nitro Gaming AN515 44 R9JM R5 4600H/8GB/512GB SSD/Nvidia GTX1650 4GB/Win10");
        itemModel7.setPrice(20990000L);
        itemModel7.setQuantity(9L);
        itemModel7.setDescription("Mua laptop Acer Nitro Gaming AN515 44 R9JM hỗ trợ game thủ chiến game cực đỉnh với AMD Ryzen 5 mạnh mẽ, trả góp 0% xét duyệt nhanh, miễn phí giao hàng khi đặt online, bảo hành chính hãng 1 năm");
        itemModel7.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/3/10/637509924794255281_acer-nitro-gaming-an515-44-den-dd.jpg");// phai link http ms dc

        ItemModel itemModel8 = new ItemModel();
        itemModel8.setCategoryId(3L);
        itemModel8.setName("Laptop Acer Swift 3x SF314 510G 57MR i5 1135G7/8G/512GSSD/Intel Iris Xe Max 4GB/Win10");
        itemModel8.setPrice(20000000L);
        itemModel8.setQuantity(20L);
        itemModel8.setDescription("Mua laptop Acer Swift 3x SF314 510G 57MR i5 trang bị intel Core i5 mạnh mẽ, thiết kế tối giản, mỏng nhẹ không ngờ. Hỗ trợ trả góp 0% xét duyệt nhanh, miễn phí giao hàng tận nơi, tặng kèm balo chất lượng");
        itemModel8.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/2/24/637497626702516153_acer-swift-3-sf314-510g-vang-dd.jpg");

        ItemModel itemModel9 = new ItemModel();
        itemModel9.setCategoryId(3L);
        itemModel9.setName("Laptop Acer Aspire 3 A315-57G-524Z i5 1035G1/8GB/512GB SSD/MX330-2G/Win10");
        itemModel9.setPrice(16590000L);
        itemModel9.setQuantity(5L);
        itemModel9.setDescription("Sở hữu ngay laptop Acer Aspire 3 A315 57G 524Z i5 với sự nâng cấp vượt trội về cấu hình, card đồ họa rời");
        itemModel9.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2020/12/24/637443973975955165_acer-aspire-3-a315-57g-den-dd.png");

        banHangDAO.insertItem(itemModel7);
        banHangDAO.insertItem(itemModel8);
        banHangDAO.insertItem(itemModel9);

        // ASUS
        ItemModel itemModel10 = new ItemModel();
        itemModel10.setCategoryId(4L);
        itemModel10.setName("Laptop Asus Gaming ROG Strix G512-IHN281T i7 10870H/8GB/512GB SSD/GTX1650Ti 4GB/WIN10");
        itemModel10.setPrice(26990000L);
        itemModel10.setQuantity(50L);
        itemModel10.setDescription("Mua laptop Asus Gaming ROG Strix G512 IHN281T trang bị intel core i7 mạnh mẽ, phong cách thiết kế tối giản nhưng đậm cá tính");
        itemModel10.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/2/25/637498501614137423_asus-g512-1650Ti-dd.jpg");// phai link http ms dc

        ItemModel itemModel11 = new ItemModel();
        itemModel11.setCategoryId(4L);
        itemModel11.setName("Laptop Asus Zenbook UX325EA-EG079T i5 1135G7/8GB/256GB SSD/Intel Iris Xe Graphics/Win10");
        itemModel11.setPrice(21690000L);
        itemModel11.setQuantity(3L);
        itemModel11.setDescription("ua laptop Asus Zenbook UX325EA EG079T i5 sở hữu màn hình viền mỏng nhất bạn từng thấy, có hỗ trợ trả góp 0%");
        itemModel11.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/2/18/637492367355006906_asus-zenbook-ux325-xam-dd-bh-2nam.jpg");

        ItemModel itemModel12 = new ItemModel();
        itemModel12.setCategoryId(4L);
        itemModel12.setName("Laptop Asus TUF Gaming FX506LH HN002T i5 10300H/8GB/512GB SSD/GTX1650 4GB/Win10");
        itemModel12.setPrice(19490000L);
        itemModel12.setQuantity(10L);
        itemModel12.setDescription("Mua laptop Asus TUF Gaming FX506LH HN002T i5 mạnh mẽ như chiến binh, chiến đấu như 1 người lính");
        itemModel12.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/2/25/637498497194722384_FA506LH-LI-dd.jpg");

        banHangDAO.insertItem(itemModel10);
        banHangDAO.insertItem(itemModel11);
        banHangDAO.insertItem(itemModel12);

        // LENOVO
        ItemModel itemModel13 = new ItemModel();
        itemModel13.setCategoryId(5L);
        itemModel13.setName("Laptop Lenovo Legion 5 Pro 16ACH6H R7 5800H/16GB/1TB SSD/16\"WQXGA-/8GB RTX3070/Win 10");
        itemModel13.setPrice(47490000L);
        itemModel13.setQuantity(3L);
        itemModel13.setDescription("Mua ngay laptop gaming Lenovo Legion 5 Pro 16ACH6H R7 mạnh mẽ, chiến game cực đã");
        itemModel13.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/5/14/637565866580115675_lenovo-legion-5-pro-xam-dd.jpg");// phai link http ms dc

        ItemModel itemModel14 = new ItemModel();
        itemModel14.setCategoryId(5L);
        itemModel14.setName("Laptop Lenovo Yoga Slim 7 14ITL05 i5 1135G7/16GB/512GB/14”FHD/Win 10");
        itemModel14.setPrice(22490000L);
        itemModel14.setQuantity(11L);
        itemModel14.setDescription("Mua laptop Lenovo Yoga Slim 7 14ITL05 i5 quá đẹp để bạn có thêm nhiều cảm hứng trong mọi hoạt động");
        itemModel14.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/3/19/637517683320084216_lenovo-yoga-slim-7-xam-dd-icon.jpg");

        ItemModel itemModel15 = new ItemModel();
        itemModel15.setCategoryId(5L);
        itemModel15.setName("Laptop Lenovo ThinkPad T14s Gen 1 i7 10510U/16GB/512GB/14”FHD/Win 10 Pro");
        itemModel15.setPrice(35990000L);
        itemModel15.setQuantity(15L);
        itemModel15.setDescription("Mua laptop Lenovo ThinkPad T14s Gen 1 i7 lựa chọn hoàn hảo cho doanh nghiệp");
        itemModel15.setImageUrl("https://images.fpt.shop/unsafe/fit-in/300x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2020/10/14/637382817190694117_thinkpad-t14s-gen1-intel-den-dd.png");

        banHangDAO.insertItem(itemModel13);
        banHangDAO.insertItem(itemModel14);
        banHangDAO.insertItem(itemModel15);
    }

}
