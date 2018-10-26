package ph.edu.apc.gamingescapeescapade;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class GamingEscapeEscapde extends AppCompatActivity {
    GPCanvas cv;
    class GPCanvas extends View {
        Paint blue;
        Bitmap bgd, t1, g1; Rect bsrc, bdst, birdsrc, birdst;
        int gsrcleft=0, gsrcright=75;
        int cgleft = 400, cgright=495;
        int gsrctop = 75, gsrcbottom = 150;
        int cgtop = 500, cgbottom = 695;
        GPCanvas(Context c) {
            super(c);
            bgd = BitmapFactory.decodeResource(getResources(),R.mipmap.scenery1);
            bsrc = new Rect(0,0,800,480);
            bdst = new Rect(0,0,480,800);
            tsrc1 = new Rect(0,200,60,280);
            tdst1 = new Rect(140,200,240+60,400+80);

            birdsrc = new Rect(gsrcleft,gsrctop,gsrcright,gsrcbottom);
            birdst = new Rect(cgleft,600,cgright,695);

            t1 = BitmapFactory.decodeResource(getResources(),R.mipmap.tile1);
            g1 = BitmapFactory.decodeResource(getResources(),R.mipmap.gannit);
            Runnable r = new CellAnimate();
            tcell= new Thread(r);
            blue = new Paint();
            blue.setColor(Color.BLUE);
        }
        Thread tcell;
        public boolean onTouchEvent (MotionEvent event){
            if (tcell != null) {
                if (!tcell.isAlive())
                    tcell.start();
            }
            return super.onTouchEvent(event);
        }
        class CellAnimateUp implements Runnable{
            @Override
            public void run() {
                while(true) {
                    gsrctop = 75*3; gsrcbottom = 75*4;
                    gsrcleft+=75; gsrcright+=75;
                    if(gsrcleft > (75*2)){gsrcleft=0; gsrcright = 75;}
                    cgleft -=10; cgright=-10;
                    gdst1.right = cgright; gdst1.top = cgtop;
                    birdsrc.right = gsrcright;
                    birdsrc.top=gsrctop;
                    gsrc1.top = gsrctop; gsrc1.bottom = gsrcbottom;
                    if (cgright<=0) break;
                    SystemClock.sleep(200);
                }
            }
        }

        class CellAnimate implements Runnable {
            public void run() {
                while(true){
                    gsrctop = 75; gsrcbottom = 75 *2;
                    gsrcleft+=75; gsrcright+=75;
                    if (gsrcleft > 75 * 2) {gsrcleft=0; gsrcright = 75;}
                    cgleft -=10; cgright-=10;
                    gdst1.left = cgleft; gdst1.right = cgright;
                    birdsrc.left = gsrcleft;
                    birdsrc.right = gsrcright;
                    birdsrc.top = gsrctop; gsrc1.bottom = gsrcbottom;
                    if (cgleft<=0) break;
                    SystemClock.sleep(200);
                }
            }
        }

        class CellAnimate1 implements Runnable {

            @Override
            public void run() {
                while (true) {
                    gsrctop = 75;
                    gsrcbottom = 75 * 2;
                    gsrcleft += 75;
                    gsrcright += 75;
                    if (gsrcleft > (75 * 2)) {
                        gsrcleft = 0;
                        gsrcright = 75;
                    }
                    cgleft -= 10;
                    cgright -= 10;
                    gdst1.left = cgleft;
                    gdst1.right = cgright;
                    birdsrc.left = gsrcleft;
                    birdsrc.right = gsrcright;
                    birdsrc.top = gsrctop;
                    birdsrc.bottom = gsrcbottom;
                    if (cgleft <= 0) {
                        startAnimate2();
                        break;
                    }
                    SystemClock.sleep(100);
                }
            }

            void startAnimate2() {
                Runnable r2 = new CellAnimateUp();

                Thread tcell2 = new Thread(r2);
                tcell2.start();
            }

            void startAnimate1() {
                cgleft = 400;
                cgright = 495;
                cgtop = 600;
                cgbottom = 695;
                gdst1.left = cgleft;
                gdst1.right = cgright;
                gdst1.top = cgtop;
                gdst1.bottom = cgbottom;
                Runnable r = new CellAnimate();
                tcell = new Thread(r);
                tcell.start();
            }
        }
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.YELLOW);

            canvas.drawBitmap(bgd,bsrc,bdst,null);
            //canvas.drawBitmap(t1,tpoolscr2,tpooldst2,null);
            // canvas.drawBitmap(t1,tcastlesrc3,tcastledst3,null);
            canvas.drawBitmap(t1,tsrc1,tdst1,null);
            canvas.drawBitmap(g1,gsrc1,gdst1,null);
            invalidate();


        }
    }


    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming_escape_escapde);
    }
}
