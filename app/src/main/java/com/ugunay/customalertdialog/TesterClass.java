package com.ugunay.customalertdialog;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

/**
 * Bu sınıf CustomAlertDialog sınıfının kullanıma sunduğu dialog pencerelerini farklı
 * kombinasyonlarda ve tasarımlarda test etmek için yazılmıştır.
 */
public class TesterClass {

    private Context context;

    /**
     * Constructor
     *
     * @param context context.
     */
    public TesterClass(Context context) {
        this.context = context;
        customAlertDialogTester();
    }

    /**
     * Bütün test fonksiyonlarını sırasıyla çağırır.
     */
    private void customAlertDialogTester() {
        testDefaultDialog();
        setColors();
        testPrepareDialogs();
        testPrepareDialogsWithLargeIcon();
        testPracticalDialogs();
        testCustomDialogs();
        testDialogsWithLargeIcon();

        setItems();
        testItems();
        testSingleChoiceItems();
        testMultiChoiceItems();
    }

    /**
     * CustomAlertDialog sınıfının varsayılan renklerini kullanarak bir alert dialog oluşturur.
     * Bu örneğin olabilmesi için bu metot setColors() metodundan önce çağrılmıştır.
     */
    private void testDefaultDialog() {
        CustomAlertDialog alertDialog = CustomAlertDialog.newInstance(context);
        alertDialog.setIcon(R.drawable.ic_report)
                .setTitle("Default Dialog")
                .setMessage("CustomAlertDialog sınıfının varsayılan renkleriyle oluşturulmuş bir alert dialog örneğidir. Yazılımcı renk değişkenlerine herhangi bir atama yapmadığında varsayılan renkler geçerli olucaktır.")
                .setPositiveButton("Onayla", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("İptal", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * CustomAlertDialog sınıfının renklerini set eder.
     * Yazılımcı projenin başında renkleri bir kez tanımlayarak projenin her yerinde aynı tasarımı kullanabilir.
     */
    private void setColors() {
        CustomAlertDialog.setTitleBackgroundColor(Color.parseColor("#370283"));
        CustomAlertDialog.setTitleTextColor(Color.MAGENTA);
        CustomAlertDialog.setMessageBackgroundColor(Color.parseColor("#4807DD"));
        CustomAlertDialog.setMessageTextColor(Color.parseColor("#EDEDED"));
        CustomAlertDialog.setPositiveButtonBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        CustomAlertDialog.setPositiveButtonTextColor(Color.CYAN);
        CustomAlertDialog.setNegativeButtonBackgroundColor(Color.parseColor("#4305CD"));
        CustomAlertDialog.setNegativeButtonTextColor(Color.GRAY);
    }

    /**
     * Hazır alert dialog tasarımları gösterir.
     * Bu alert dialoglarda sadece mesajın metin ve arka plan rengi yazılımcının seçtiği renkler olabilirken
     * diğer renkler sabittir, yazılımcının değiştirmesine kapalıdır.
     * Bu yüzden hazır tasarımlara sahip alert dialoglardır.
     * Sadece başlık ve mesaj bilgilerini dışarıdan alarak pratik bir kullanm sunar.
     * Bir tek uyarı alert dialogu ekstra olarak Evet butonu için OnClickListener olayını dışarıdan alır.
     * İlk üç alert dialog pencereden çıkış için Tamam butonu bulundurur.
     * Uyarı alert dialogun da zaten Evet-Hayır butonları vardır.
     */
    private void testPrepareDialogs() {
        CustomAlertDialog.newInstance(context).showInfoDialog("Info Dialog", "Kullanıcıyı herhangi bir konuda bilgilendirmek isterseniz bu alert dialog penceresini kullanabilirsiniz.");
        CustomAlertDialog.newInstance(context).showSuccessDialog("Success Dialog", "Kullanıcının yaptığı girişim başarılı sonuçlandığında işlem sonucuyla ilgili kullanıcıya bilgi vermek için bu alert dialog penceresini kullanabilirsiniz.");
        CustomAlertDialog.newInstance(context).showErrorDialog("Error Dialog", "Kullanıcının yaptığı girişim hatalı sonuçlandığında işlem sonucuyla ilgili kullanıcıya bilgi vermek için bu alert dialog penceresini kullanabilirsiniz.");
        CustomAlertDialog.newInstance(context).showWarningDialog("Warning Dialog", "Kullanıcının yaptığı girişim Evet veya Hayır ile sonuçlanacak bir uyarı gerektiriyorsa bu alert dialog penceresini kullanabilirsiniz.",
                new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        dialog.dismiss();
                    }
                });
    }

    /**
     * Büyük ikonlu hazır alert dialog tasarımları gösterir.
     */
    private void testPrepareDialogsWithLargeIcon() {
        CustomAlertDialog.newInstance(context).showInfoDialog("Başlıksız InfoDialog örneği. Kullanıcıyı herhangi bir konuda bilgilendirmek isterseniz bu alert dialog penceresini kullanabilirsiniz.");
        CustomAlertDialog.newInstance(context).showSuccessDialog("Başlıksız SuccessDialog örneği. Kullanıcının yaptığı girişim başarılı sonuçlandığında işlem sonucuyla ilgili kullanıcıya bilgi vermek için bu alert dialog penceresini kullanabilirsiniz.");
        CustomAlertDialog.newInstance(context).showErrorDialog("Başlıksız ErrorDialog örneği. Kullanıcının yaptığı girişim hatalı sonuçlandığında işlem sonucuyla ilgili kullanıcıya bilgi vermek için bu alert dialog penceresini kullanabilirsiniz.");
        CustomAlertDialog.newInstance(context).showWarningDialog("Başlıksız WarningDialog örneği. Kullanıcının yaptığı girişim Evet veya Hayır ile sonuçlanacak bir uyarı gerektiriyorsa bu alert dialog penceresini kullanabilirsiniz.",
                new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        dialog.dismiss();
                    }
                });
    }

    /**
     * Kullanımı pratik olan alert dialoglar oluşturur.
     * Tasarım renkerli için yazılımcının belirlediği renkler veya varsayılan renkler geçerlidir.
     * İki tasarım da pencereden çıkış için Tamam butonu bulundurur.
     */
    private void testPracticalDialogs() {
        CustomAlertDialog.newInstance(context).showDialogWithoutIcon("İkonsuz Başlık", "Bu alert dialog ikon bulundurmayan başlık ve mesaj bilgisini dışarıdan alan pratik bir alert dialog örneğidir.");
        String message = "Bu alert dialog başlık bilgisi bulundurmayan sadece mesaj bilgisini dışarıdan alan pratik bir alert dialog örneğidir.";
        CustomAlertDialog.newInstance(context).showDialogWithoutTitle(message + " Bu örnekte pozitif butonun textColor rengi dışarıdan verilmiştir.", Color.GREEN);
        CustomAlertDialog.newInstance(context).showDialogWithoutTitle(message + " Tamam butonunun konumu ve tasarımı farklı yapılmıştır. Daha sade bir tasarım elde edilmiştir.");
    }

    /**
     * Farklı renklere ve kombinasyonlara sahip alert dialog örnekleri oluşturur.
     */
    private void testCustomDialogs() {
        CustomAlertDialog alertDialog_1 = CustomAlertDialog.newInstance(context);
        alertDialog_1.setIcon(R.drawable.ic_report)
                .setMessage("İkonu olan fakat başlığı olmayan absürt bir alert dialog örneğidir. Başlığı olmadığı için başlık arka plan rengi varsayılan renk olmuştur.")
                .setPositiveButton("Absürt Örnek", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Absürt bir örnek...", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();

        CustomAlertDialog alertDialog_2 = CustomAlertDialog.newInstance(context);
        alertDialog_2.setIcon(R.drawable.ic_report)
                .setIconTintColor(Color.GREEN)
                .setTitle("Özelleştirilmiş Dialog", Color.BLACK, Color.GREEN)
                .setMessage("Sadece bu alert dialog için bütün renkleri özelleştirilmiş alert dialog örneğidir. Butonlara ikon ataması da yapılmıştır.",
                        Color.parseColor("#444444"), Color.YELLOW)
                .setPositiveButton("Onayla", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }, context.getResources().getColor(R.color.colorAccent), Color.RED)
                .setNegativeButton("İptal", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }, Color.parseColor("#458699"), Color.BLUE)
                .setPositiveButtonIcon(android.R.drawable.checkbox_on_background)
                .setNegativeButtonIcon(android.R.drawable.ic_delete)
                .setCancelable(false)
                .show();

        CustomAlertDialog alertDialog_3 = CustomAlertDialog.newInstance(context);
        alertDialog_3.setIcon(R.drawable.ic_report)
                .setTitle("Özel Dialog")
                .setMessage("Bütün UI elemanları kullanılmış fakat hiçbir renk ataması yapılmamış bir alert dialog örneğidir. Bu örneğin amacı yazılımcının seçtiği bütün renkleri test etmek içindir.")
                .setPositiveButton("Onayla", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("İptal", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Farklı bir tasarım olan büyük ikonlu alert dialog örnekleri oluşturur.
     */
    private void testDialogsWithLargeIcon() {
        CustomAlertDialog alertDialog_1 = CustomAlertDialog.newInstance(context);
        alertDialog_1.showPermissionDialog(R.drawable.ic_report, "İzin Türü",
                "Kullanıcıdan herhangi bir uygulama izni almadan önce o iznin alınma sebebini açıklamak için bu alert dialog penceresini kullanabilirsiniz.",
                new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "İzin işlemine geçiliyor.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

        CustomAlertDialog alertDialog_2 = CustomAlertDialog.newInstance(context);
        alertDialog_2.setDialogWithLargeIcon(R.drawable.ic_report, null, "Başlığı ve negatif butonu olmayan, büyük ikonlu bir alert dialog örneğidir.")
                .setPositiveButton("Tamam", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();

        CustomAlertDialog alertDialog_3 = CustomAlertDialog.newInstance(context);
        alertDialog_3.setDialogWithLargeIcon(R.drawable.ic_report, "İkonlu Butonlar", "Butonlar ikonlu olarak denenmiştir. Ayrıca pozitif ve negatif butonların yüksekliklerinin farklı olması durumu da ele alınmıştır. Bu durumda yüksekliği düşük olan butonun arka plan rengi eksik görünecekti. Eksik olan yerler dialog.xml'den gelen background_color olarak görünecekti. Bu da renk uyumsuzluğu oluşturacaktı. Bu sorunu ortadan kaldırmak için butonların yükseklikleri kontrol edilmiştir. pnlButtons'ın arka plan rengi yüksekliği düşük olan butonun arka plan rengi olarak atanmıştır. ")
                .setIconTintColor(Color.CYAN)
                .setCancelable(false)
                .setPositiveButtonIcon(R.drawable.ic_report)
                .setPositiveButton("Anladım", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Bunu Bir Daha Gösterme", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButtonIcon(android.R.drawable.ic_menu_close_clear_cancel)
                .show();

        CustomAlertDialog alertDialog_4 = CustomAlertDialog.newInstance(context);
        alertDialog_4.setDialogWithLargeIcon(R.drawable.ic_report, "Large Icon", "Büyük ikonlu alert dialog denemesidir. Bu tasarımda başlık arka plan rengi ile mesaj arka plan rengi aynıdır. Pencerede bir bütünlük sağlanmıştır. Başlık bilgisini null girerseniz başlık görünmeyecektir. Farklı bir tasarımda alert dialog görüntülemek için bu pencere kullanılabilir.")
                .setIconTintColor(Color.CYAN)
                .setPositiveButton("Devam Et", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("İptal", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    /**
     * Mesaj yerine verilen CharSequence listesini (items) gösteren alert dialog örnekleri oluşturur.
     */
    private void testItems() {
        CustomAlertDialog alertDialog_1 = CustomAlertDialog.newInstance(context);
        alertDialog_1.setDialogWithLargeIcon(R.drawable.ic_report, "SetItems Örneği", "Mesaj tanımlansa bile görünmeyecektir.")
                .setIconTintColor(Color.CYAN)
                .setCancelable(false)
                .setPositiveButton("Devam Et", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setItems(items, new CustomAlertDialog.OnItemClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog, int which) {
                        Toast.makeText(context, "Tıklanan eleman : " + items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("İptal", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif butona basıldı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();

        CustomAlertDialog alertDialog_2 = CustomAlertDialog.newInstance(context);
        alertDialog_2.setIcon(R.drawable.ic_report)
                .setIconTintColor(Color.GREEN)
                .setTitle("SetItems Örneği")
                .setMessage("Deneme 11111111 dnjsjdjgjnsd gnsdlgsjldjg sdljglskdgs")
                .setItems(items, new CustomAlertDialog.OnItemClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog, int which) {
                        Toast.makeText(context, "Tıklanan eleman : " + items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }, context.getResources().getColor(R.color.colorPrimary))
                .setMessage("Deneme 2222222 dnjsjdjgjnsd gnsdlgsjldjg sdljglskdgs")
                .show();
    }

    /**
     * Mesaj yerine verilen CharSequence listesi (items) üzerinde sadece bir elemanın seçim
     * yapılabileceği alert dialog örnekleri oluşturur.
     */
    private void testSingleChoiceItems() {
        CustomAlertDialog alertDialog_1 = CustomAlertDialog.newInstance(context);
        alertDialog_1.setIcon(android.R.drawable.ic_menu_share)
                .setTitle("SingleChoiceItems Örneği")
                .setSingleChoiceItems(items, 10, new CustomAlertDialog.OnItemClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog, int which) {
                        Toast.makeText(context, "Tıklanan eleman : " + items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Tamam", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif buton tıklandı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("İptal", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif buton tıklandı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();

        CustomAlertDialog alertDialog_2 = CustomAlertDialog.newInstance(context);
        alertDialog_2.setDialogWithLargeIcon(R.drawable.ic_report, "SingleChoiceItems Örneği", null)
                .setIconTintColor(Color.CYAN)
                .setSingleChoiceItems(items, 33, new CustomAlertDialog.OnItemClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog, int which) {
                        Toast.makeText(context, "Tıklanan eleman : " + items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Tamam", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif buton tıklandı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("İptal", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif buton tıklandı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Mesaj yerine verilen CharSequence listesi (items) üzerinde çoklu eleman seçim işlemi
     * yapılabileceği alert dialog örnekleri oluşturur.
     */
    private void testMultiChoiceItems() {
        boolean[] checkedItems = new boolean[50];
        for (int i = 0; i < checkedItems.length; i++) {
            checkedItems[i] = i % 3 == 0;
        }

        CustomAlertDialog alertDialog_1 = CustomAlertDialog.newInstance(context);
        alertDialog_1.setIcon(android.R.drawable.checkbox_on_background)
                .setTitle("MultiChoiceItems Örneği")
                .setMultiChoiceItems(items, checkedItems, new CustomAlertDialog.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog, int which, boolean isChecked) {
                        Toast.makeText(context, "Tıklanan eleman : " + items[which] + " - " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Tamam", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif buton tıklandı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("İptal", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif buton tıklandı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();

        CustomAlertDialog alertDialog_2 = CustomAlertDialog.newInstance(context);
        alertDialog_2.setDialogWithLargeIcon(R.drawable.ic_report, "MultiChoiceItems Örneği", null)
                .setIconTintColor(Color.CYAN)
                .setMultiChoiceItems(items, checkedItems, new CustomAlertDialog.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog, int which, boolean isChecked) {
                        Toast.makeText(context, "Tıklanan eleman : " + items[which] + " - " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Seçim İşlemini Bitirdim", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Pozitif buton tıklandı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("İptal Et", new CustomAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        Toast.makeText(context, "Negatif buton tıklandı.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButtonIcon(android.R.drawable.checkbox_on_background)
                .setNegativeButtonIcon(android.R.drawable.ic_menu_close_clear_cancel)
                .show();
    }


    private CharSequence[] items;

    /**
     * items dizisini test dialoglar için doldurur.
     */
    private void setItems() {
        items = new CharSequence[50];
        for (int i = 0; i < items.length; i++) {
            items[i] = "deneme " + i + " asdfghjklşi";
        }
    }

}
