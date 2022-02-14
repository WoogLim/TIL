package org.techtown.sampleparcelable;

import android.os.Parcel;
import android.os.Parcelable;

// 직렬화를 위해 Parcelable 사용한다. 자바의 Serializable 로 생각할것
public class SimpleData implements Parcelable {
    
    // 인스턴스 변수
    int number;
    String message;

    public SimpleData(int num, String msg){
        number = num;
        message = msg;
    }

    // SimpleData 생성자는 Parcel을 파라미터로 받는다. 아래 메서드를 이용해 데이터를 읽어들인다.
    // 외부에서 클래스 생성시 사용. 아래 CREATOR가 사용하는 생성자
    public SimpleData(Parcel src){
        // Parcel 객체에서 읽기
        number = src.readInt();
        message = src.readString();
    }

    // (역직렬화) 새로운 객체가 만들어지는 코드로 new SimpleData와 같이 객체를 만드는 부분을 알 수 있다.
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        // CREATOR 상수 정의
        public SimpleData createFromParcel(Parcel in){
            return new SimpleData(in); // SimpleData 생성자를 호출해 Parcel 객체에서 읽기
        }

        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };

    public int describeContents(){
        return 0;
    }

    // 이 SimpleData 객체 안에 있는 데이터를 Parcel(직렬화)로 만드는 역할
    public void writeToParcel(Parcel dest, int flags) { // Parcel 객체로 쓰기
        dest.writeInt(number);
        dest.writeString(message);
    }
}
