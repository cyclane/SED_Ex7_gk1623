package fi.iki.jka;

import java.awt.Component;

interface DialogService {
  void showMessageDialog(Component parent, String message);
  void showMessageDialog(Component parent, String message, String title, int messageType);
}
