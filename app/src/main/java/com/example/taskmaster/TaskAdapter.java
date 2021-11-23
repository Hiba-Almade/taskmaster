package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<Task> allTask;

    public TaskAdapter(List<Task> tasks) {
        this.allTask = tasks;

    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public Task task;
        public View itemView;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task=allTask.get(position);
        TextView title=holder.itemView.findViewById(R.id.titleFragment);
        TextView state=holder.itemView.findViewById(R.id.stateFragment);
        TextView body=holder.itemView.findViewById(R.id.bodyFragment);

        title.setText(holder.task.getTitle());
        state.setText(holder.task.getState());
        body.setText(holder.task.getBody());

        ConstraintLayout constraintLayout = holder.itemView.findViewById(R.id.fragmentLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent taskDetailsIntent = new Intent(v.getContext(), taskDetails.class);
                taskDetailsIntent.putExtra("title",title.getText().toString());
                taskDetailsIntent.putExtra("body",body.getText().toString());
                taskDetailsIntent.putExtra("state",state.getText().toString());
//                taskDetailsIntent.putExtra("id",holder.task.getId());
                v.getContext().startActivity(taskDetailsIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return allTask.size();
    }

}
