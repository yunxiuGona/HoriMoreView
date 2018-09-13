# HoriMoreView
HoriMoreView is an library to easily create hori-list-view
There Insetted an RecyclerView Inside,so it can support types of holders in it

![image](https://github.com/15563988825/HoriMoreView/blob/master/1.gif)

for use <br>
1、In your layout:
```xml
    <com.salterwater.horimoreview.HoriMoreView
        android:id="@+id/hmv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
    </com.salterwater.horimoreview.HoriMoreView>
```
2、In java code:
```java
HoriMoreView hmv;
hmv.setDragListener(this);
        hmv.setHoriMoreViewDelegate(new HRecyclerView.HoriMoreViewDelegate() {
            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public int getView(@NonNull ViewGroup parent, int position) {
                if(position==5) {
                    return R.layout.adp_rv1;
                }else{
                    return R.layout.adp_rv2;
                }
            }
            @Override
            public void bindView(View view, int position) {
                //view.setText  view.setOnClickListener  view.setVisivle  etc...
            }
        });
```
* getCount():Just like "getItemCount" in RecyclerView,get item count of data list.<br>
* getView():Just like "onCreateViewHolder" in RecyclerView,should return id of layout.<br>
* bindView():Just like "onBindViewHolder" in RecyclerView,do something just you like.<br>

setDragListener(DragListener dragListener);
-------
when:More View has Released
else:the more view wonnot show when draging if set null or havenot setted

setFooterDrawer(BaseFooterDrawer footerDrawer)
-------
can set the drawer for footer
```java
hvm.setFooterDrawer(new BezierFooterDrawer.Builder(context,
                Color.parseColor("#EEEEEE"))
                .setIconDrawable(null)
                .setTextColor(Color.parseColor("#999999"))
                .setTextSize(10)
                .setRectFooterThick(0)
                .setBezierDragThreshold(80)
                .setNormalString("More")
                .setEventString("Release")
                .build());
```
