package rs.edu.raf.rma.homework3.repository.web.model;

public class Resource <T> {

    private T mData;
    private boolean mIsSuccessfull;

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public boolean isSuccessfull() {
        return mIsSuccessfull;
    }

    public void setSuccessfull(boolean successfull) {
        mIsSuccessfull = successfull;
    }

    public Resource(T data, boolean isSuccessfull) {
        mData = data;
        mIsSuccessfull = isSuccessfull;
    }
}
