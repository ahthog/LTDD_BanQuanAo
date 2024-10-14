package GioHang;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {
    private MutableLiveData<Integer> tienGoc1 = new MutableLiveData<>(0);
    private MutableLiveData<Integer> tienGoc2 = new MutableLiveData<>(0);
    private MutableLiveData<Integer> tienGoc3 = new MutableLiveData<>(0);

    private MutableLiveData<Integer> tienGiam1 = new MutableLiveData<>(0);
    private MutableLiveData<Integer> tienGiam2 = new MutableLiveData<>(0);
    private MutableLiveData<Integer> tienGiam3 = new MutableLiveData<>(0);

    private MutableLiveData<Integer> tongTien1 = new MutableLiveData<>(0);
    private MutableLiveData<Integer> tongTien2 = new MutableLiveData<>(0);
    private MutableLiveData<Integer> tongTien3 = new MutableLiveData<>(0);

    // Getter
    public LiveData<Integer> getTienGoc1() {
        return tienGoc1;
    }

    public LiveData<Integer> getTienGoc2() {
        return tienGoc2;
    }

    public LiveData<Integer> getTienGoc3() {
        return tienGoc3;
    }

    public LiveData<Integer> getTienGiam1() {
        return tienGiam1;
    }

    public LiveData<Integer> getTienGiam2() {
        return tienGiam2;
    }

    public LiveData<Integer> getTienGiam3() {
        return tienGiam3;
    }

    public LiveData<Integer> getTongTien1() {
        return tongTien1;
    }

    public LiveData<Integer> getTongTien2() {
        return tongTien2;
    }

    public LiveData<Integer> getTongTien3() {
        return tongTien3;
    }

    // Setter
    public void setTienGoc1(int value) {
        tienGoc1.setValue(value);
    }

    public void setTienGoc2(int value) {
        tienGoc2.setValue(value);
    }

    public void setTienGoc3(int value) {
        tienGoc3.setValue(value);
    }

    public void setTienGiam1(int value) {
        tienGiam1.setValue(value);
    }

    public void setTienGiam2(int value) {
        tienGiam2.setValue(value);
    }

    public void setTienGiam3(int value) {
        tienGiam3.setValue(value);
    }

    public void setTongTien1(int value) {
        tongTien1.setValue(value);
    }

    public void setTongTien2(int value) {
        tongTien2.setValue(value);
    }

    public void setTongTien3(int value) {
        tongTien3.setValue(value);
    }
}
