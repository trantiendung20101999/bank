package com.example.bank;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank.api.ApiService;
import com.example.bank.model.BankSavingBookResponse;
import com.example.bank.model.InterestRateSaveBook;
import com.example.bank.model.SaveBankSavingBook;
import com.example.bank.model.UserSaveBook;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSavingRevAdapter extends RecyclerView.Adapter<CreateSavingRevAdapter.NormalHolder> {

    List<String> listString ;
    String type;


    public CreateSavingRevAdapter(List<String> listString,String type) {
        this.listString = listString;
        this.type=type;
    }

    @NonNull
    @Override
    public NormalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_term,parent,false);
        return new NormalHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NormalHolder holder, int position) {

        String term = listString.get(position);
        if(term!=null)
        {
            holder.btn.setText(term);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CreateSavingActivity.edMoney.getText().toString().equalsIgnoreCase("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Hãy nhập vào số tiền ");
                        builder.create().show();
                    }
                    else {
                        SaveBankSavingBook saveBankSavingBook = new SaveBankSavingBook();
                        saveBankSavingBook.setMoney(Double.parseDouble(CreateSavingActivity.edMoney.getText().toString()));
                        saveBankSavingBook.setBranch("ACB BANK");
                        UserSaveBook userSaveBook = new UserSaveBook();
                        userSaveBook.setId(MainActivity.u.getId());
                        long id = 0;
                        int times = position + 1;
                        if (type.equalsIgnoreCase("normal")) {
                            id = position + 1;
                        } else {
                            id = position + 13;
                        }
                        InterestRateSaveBook interestRateSaveBook = new InterestRateSaveBook(id, times);
                        saveBankSavingBook.setUser(userSaveBook);
                        saveBankSavingBook.setInterestrate(interestRateSaveBook);


                        if (saveBankSavingBook.getMoney() > MainActivity.u.getMoney()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setMessage("Bạn không đủ tiền ");
                            builder.create().show();
                        } else {
                            ApiService.apiService.SaveBankSavingBook(saveBankSavingBook, "Bearer " + MainActivity.u.getAccessToken())
                                    .enqueue(new Callback<BankSavingBookResponse>() {
                                        @Override
                                        public void onResponse(Call<BankSavingBookResponse> call, Response<BankSavingBookResponse> response) {
                                            Intent intent = new Intent(v.getContext(), LoginActivity.class);
                                            intent.putExtra("alert", "Đăng nhập lại để cập nhật sổ ");
                                            intent.putExtra("username", MainActivity.u.getUsername());
                                            v.getContext().startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<BankSavingBookResponse> call, Throwable t) {

                                        }
                                    });

                        }
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(listString!=null)
        {
            return listString.size();
        }
        return 0;
    }

    class NormalHolder extends RecyclerView.ViewHolder{

        Button btn;

        public NormalHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btnThang);
        }
    }
}
