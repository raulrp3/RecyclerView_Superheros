package com.example.recyclerviewapplication.Helpers

import android.R
import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeToDeleteCallback(context: Context): ItemTouchHelper.Callback() {

    private val mClearPaint: Paint = Paint();
    private val mBackground: ColorDrawable = ColorDrawable();
    private val deleteDrawable: Drawable;
    private val intrinsicWidth: Int;
    private val intrinsicHeight: Int;

    init {
        mClearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        deleteDrawable = ContextCompat.getDrawable(context, R.drawable.ic_delete)!!;

        val wrapperDrawable = DrawableCompat.wrap(deleteDrawable);
        DrawableCompat.setTint(wrapperDrawable, Color.WHITE);

        intrinsicWidth = deleteDrawable.intrinsicWidth;
        intrinsicHeight = deleteDrawable.intrinsicHeight;
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false;
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView;
        val itemHeight = itemView.height;

        mBackground.color = Color.parseColor("#FF180A");
        mBackground.setBounds(
            itemView.right + dX.toInt(),
            itemView.top,
            itemView.right,
            itemView.bottom
        );
        mBackground.draw(c);

        val iconTop = itemView.top + (itemHeight - intrinsicHeight) / 2;
        val iconMargin = (itemHeight - intrinsicHeight) / 2;
        val iconLeft = itemView.right - iconMargin - intrinsicWidth;
        val iconRight = itemView.right - iconMargin;
        val iconBottom = iconTop + intrinsicHeight;

        deleteDrawable.setBounds(iconLeft, iconTop, iconRight, iconBottom);
        deleteDrawable.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.7f;
    }
}