package com.poly.lab5.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.lab5.db.DB; // Import lớp DB giả lập cơ sở dữ liệu
import com.poly.lab5.entity.Item; // Import lớp thực thể Item

@SessionScope // Annotation này chỉ định rằng một bean duy nhất của lớp này sẽ được tạo cho mỗi phiên HTTP. Giỏ hàng của mỗi người dùng sẽ được lưu trữ riêng biệt.
@Service // Annotation này đánh dấu lớp này là một tầng dịch vụ (Service) trong ứng dụng Spring, cho phép Spring quản lý nó như một bean.
public class ShoppingCartServiceImpl implements ShoppingCartService { // Khai báo lớp ShoppingCartServiceImpl triển khai interface ShoppingCartService
    private final Map<Integer, Item> cart = new HashMap<>(); // Khai báo một Map để lưu trữ các mặt hàng trong giỏ hàng, với key là ID sản phẩm và value là đối tượng Item. 'final' đảm bảo map được khởi tạo một lần.

    /**
     * Thêm một mặt hàng vào giỏ hàng hoặc tăng số lượng nếu đã tồn tại.
     * @param id ID của mặt hàng cần thêm
     * @return Mặt hàng đã được thêm hoặc cập nhật
     */
    @Override // Ghi đè phương thức từ interface ShoppingCartService
    public Item add(Integer id) { // Phương thức thêm một sản phẩm vào giỏ hàng dựa vào ID
        if (cart.containsKey(id)) { // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            Item item = cart.get(id); // Nếu có, lấy mặt hàng hiện có từ giỏ hàng
            item.setQuantity(item.getQuantity() + 1); // Tăng số lượng của mặt hàng lên 1
            return item; // Trả về mặt hàng đã được cập nhật
        } else { // Nếu sản phẩm chưa có trong giỏ hàng
            Item itemFromDB = DB.items.get(id); // Lấy thông tin sản phẩm từ "cơ sở dữ liệu" giả lập (DB.items)
            if (itemFromDB != null) { // Kiểm tra xem sản phẩm có tồn tại trong DB không
                // Tạo một bản sao của sản phẩm từ DB và đặt số lượng là 1
                Item newItem = new Item(itemFromDB.getId(), itemFromDB.getName(), itemFromDB.getPrice(), 1); // Tạo một đối tượng Item mới với số lượng là 1
                cart.put(id, newItem); // Thêm mặt hàng mới vào giỏ hàng
                return newItem; // Trả về mặt hàng mới được thêm
            } else { // Nếu sản phẩm không tìm thấy trong DB
                throw new IllegalArgumentException("Product with id " + id + " not found in DB"); // Ném ra một ngoại lệ báo lỗi
            }
        }
    }

    /**
     * Xóa một mặt hàng khỏi giỏ hàng.
     * @param id ID của mặt hàng cần xóa
     */
    @Override // Ghi đè phương thức từ interface ShoppingCartService
    public void remove(Integer id) { // Phương thức xóa một sản phẩm khỏi giỏ hàng
        cart.remove(id); // Sử dụng phương thức remove của Map để xóa mặt hàng theo ID
    }

    /**
     * Cập nhật số lượng của một mặt hàng trong giỏ hàng.
     * @param id ID của mặt hàng cần cập nhật
     * @param quantity Số lượng mới
     * @return Mặt hàng đã được cập nhật
     */
    @Override // Ghi đè phương thức từ interface ShoppingCartService
    public Item update(Integer id, int quantity) { // Phương thức cập nhật số lượng của một mặt hàng
        if (cart.containsKey(id)) { // Kiểm tra xem mặt hàng có tồn tại trong giỏ hàng không
            Item item = cart.get(id); // Lấy mặt hàng từ giỏ hàng
            item.setQuantity(quantity); // Cập nhật số lượng mới cho mặt hàng
            return item; // Trả về mặt hàng đã được cập nhật
        }
        throw new IllegalArgumentException("Item not found in the cart!"); // Nếu không tìm thấy, ném ra ngoại lệ
    }

    /**
     * Xóa tất cả các mặt hàng khỏi giỏ hàng.
     */
    @Override // Ghi đè phương thức từ interface ShoppingCartService
    public void clear() { // Phương thức xóa tất cả các mặt hàng khỏi giỏ hàng
        cart.clear(); // Gọi phương thức clear() của Map để xóa toàn bộ nội dung
    }

    /**
     * Lấy tất cả các mặt hàng trong giỏ hàng.
     * @return Một Collection chứa các mặt hàng
     */
    @Override // Ghi đè phương thức từ interface ShoppingCartService
    public Collection<Item> getItems() { // Phương thức lấy tất cả các mặt hàng trong giỏ hàng
        return cart.values(); // Trả về một Collection chứa tất cả các giá trị (đối tượng Item) từ Map
    }

    /**
     * Lấy tổng số lượng các mặt hàng trong giỏ hàng.
     * @return Tổng số lượng
     */
    @Override // Ghi đè phương thức từ interface ShoppingCartService
    public int getCount() { // Phương thức tính tổng số lượng các mặt hàng trong giỏ
        return cart.values().stream() // Chuyển đổi Collection các Item thành một Stream
                .mapToInt(Item::getQuantity) // Ánh xạ mỗi Item thành số lượng (int) của nó
                .sum(); // Tính tổng tất cả các số lượng
    }

    /**
     * Lấy tổng thành tiền của tất cả các mặt hàng trong giỏ hàng.
     * @return Tổng thành tiền
     */
    @Override // Ghi đè phương thức từ interface ShoppingCartService
    public double getAmount() { // Phương thức tính tổng thành tiền của giỏ hàng
        return cart.values().stream() // Chuyển đổi Collection các Item thành một Stream
                .mapToDouble(item -> item.getPrice() * item.getQuantity()) // Ánh xạ mỗi Item thành tổng tiền (giá * số lượng) của nó
                .sum(); // Tính tổng tất cả các giá trị tiền
    }
}
