import { Side } from './enums/side.enum';

export class Order {
  side = Side.BUY;
  quantity = 0;
  price = 0.0;
}
