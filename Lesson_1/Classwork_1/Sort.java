
public class Sort {

	public static void main(String[] args) {
		int[] array = new int[]{4, 2, 5, 1, 9, 3, 7, 6, 8, 0};

		// bubbleSort(array);
		// directSort(array);
		// insertSort(array);
		// quickSort(array,0, array.length - 1);
		heapifySortCreate(array);

		for (int j : array) {
			System.out.print(j + " ");
		}
	}

	/**
	 * Сортировка пузырьком.
	 *
	 * Один из наиболее простых алгоритмов сортировки массива – пузырьковая
	 * сортировка. Нагляднее всего работу этого алгоритма можно продемонстрировать в
	 * вертикальном массиве – наиболее легкие элементы стремятся вверх, словно
	 * пузырьки воздуха в жидкости.
	 * Базовый алгоритм предполагает, что каждый элемент необходимо сравнить с
	 * соседним и, если правый элемент меньше левого, то их меняют местами. Алгоритм
	 * повторяется до тех пор, пока все элементы в массиве не выстроятся в нужном
	 * порядке.
	 * Как можно понять из примера, количество шагов для сортировки будет отличаться в
	 * зависимости от того, в каком порядке изначально стоят элементы массива. Если
	 * массив уже отсортирован, то достаточно пройти по массиву 1 раз, чтобы убедиться
	 * в этом. При этом никаких операций перестановки элементов выполнено не будет. В
	 * такой ситуации сложность алгоритма будет O(n). В тоже время, если самый
	 * маленький элемент находится в самом конце, то количество проходов по массиву
	 * размером n будет так же достигать n, что дает нам сложность O(n2
	 * ). Этот пример
	 * явно иллюстрирует, почему при оценке алгоритма не вычисляют реальное
	 * количество необходимых шагов, для получения результата. Даже один и тот же
	 * алгоритм может давать различное количество шагов для входных данных одного
	 * размера. Это подводит нас к еще одной особенности оценки сложности алгоритма.
	 * Один и тот же алгоритм может давать различные результаты для разных входящих
	 * данных, поэтому для оценки некоторых алгоритмов используются понятия
	 * максимальной (предельной) сложности и ожидаемой сложности.
	 * Максимальная сложность – количество шагов для обработки наиболее
	 * неблагоприятного состояния входных данных.
	 * Ожидаемая сложность – это вариант, который будет релевантен для большей
	 * части возможных кейсов.
	 * Для некоторых алгоритмов ожидаемая сложность будет совпадать с максимальной,
	 * в каких-то – нет. В приведенном выше алгоритме пузырьковой сортировки
	 * максимальной сложностью вычисления будет O(n2
	 * ) и эта же сложность является
	 * ожидаемой для этого алгоритма – для данного алгоритма очень небольшое
	 * количество кейсов дает сложность ниже указанной, а значит в большинстве
	 * случаев она будет стремиться именно к максимальной.
	 */
	public static void bubbleSort(int[] array) {
		boolean finish;
		do {
			finish = true;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					finish = false;
				}
			}
		} while (!finish);
	}

	/**
	 * Сортировка выбором.
	 * Так же очень простой алгоритм сортировки, который предполагает поиск
	 * наименьшего (или наибольшего) значения правее от сравниваемого элемента. В
	 * случае, если такой элемент найден – происходит перестановка с начальным
	 * элементом.
	 * Данный алгоритм очень похож на пузырьковую сортировку, за тем исключением,
	 * что для его записи удобнее использовать не цикл while, а 2 циклов for, вложенные
	 * друг в друга.
	 * Это наглядно приводит нас так же к сложности O(n2
	 * ). В данном примере это
	 * несколько нагляднее – внутри одного алгоритма со сложностью O(n) вызывается
	 * еще один алгоритм O(n), что по правилам перемножения даст как раз сложность
	 * O(n2
	 * ). По факту, подобный подход к сортировке уменьшает количество реальных
	 * операций перестановки, в сравнении с сортировкой пузырьком, но общее
	 * количество сравнений будет точно таким же.
	 */
	public static void directSort(int[] array){
		for (int i = 0; i < array.length - 1; i++) {
			int minPosition = i;
			for (int j = i + 1; j < array.length; j++){
				if (array[j] < array[minPosition]) {
					minPosition = j;
				}
			}
			if (i != minPosition) {
				int temp = array[i];
				array[i] = array[minPosition];
				array[minPosition] = temp;
			}
		}
	}

	/**
	 * Сортировка вставками.
	 * Так же, как нечто среднее, между сортировкой пузырьком и выбором, можно
	 * выделить сортировку вставками. Принцип работы у нее точно такой же, как у
	 * предыдущей, только после сравнения двух элементов мы не запоминаем индекс
	 * наименьшего (наибольшего) из элементов, а сразу производим перестановку.
	 * Точно так же, как и обе предыдущие сортировки, сложность данного алгоритма
	 * равна O(n2
	 * ). Как мы видим, все 3 рассмотренных алгоритма совершенно идентичны
	 * с точки зрения сложности. А также мы можем сказать, что использовать алгоритмы
	 * подобной сложности нельзя на данных большого размера, т.к. скорость выполнения
	 * алгоритма будет очень медленной. Попробуйте взять любой из рассмотренных
	 * алгоритмов и отсортировать массив, в котором 1 000 000 элементов. В среднем,
	 * пузырьковая сортировка может занять до получаса, в зависимости от мощности
	 * вашего компьютера, что явно не подходит для использования в массивах подобного
	 * размера.
	 */
	public static void insertSort(int[] array){
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	/**
	 * Быстрая сортировка (quicksort).
	 * Чаще всего, когда мы используем сортировку, уже реализованную в штатных
	 * средствах языка программирования или библиотеки, под капотом мы встретим
	 * именно быструю сортировку. Суть быстрой сортировки – разделить массив на 2
	 * части таким образом, чтобы справа все числа были больше, чем слева, при этом их
	 * порядок относительно друг друга не важен. Это позволит не сравнивать элементы
	 * справа с элементами слева больше 1 раза, как раз для достижения их разделения
	 * на 2 части. И далее этот же подход будет применяться для каждой из получившихся
	 * частей, равно как предусматривает принцип «разделяй и властвуй». При этом, в
	 * отличии от бинарного поиска, количество операций в момент разделения не
	 * константное, а линейное – необходимо сравнить все элементы правой и левой
	 * части с неким эталоном и при необходимо – поменять их местами. В данном
	 * алгоритме такой элемент называется пивотом.
	 * При этом нет единого эталонного алгоритма выбора пивота. Это может быть
	 * абсолютно любой элемент массива – средний, крайний, случайный и т.д.
	 * Берутся 2 маркера, стоящие в 2 крайних позициях и начинаются двигаться друг к
	 * другу, сравнивая элементы с пивотом. Как только левый маркер находит элемент
	 * больше пивота, он останавливается до тех пор, пока правый маркер не встретит
	 * элемент меньше пивота. Это сигнал к тому, что данные элементы необходимо
	 * поменять местами, чтобы соблюсти условие – все элементы левее пивота меньше
	 * либо равны ему, а справа – больше либо равны. Маркеры продолжаются двигаться
	 * друг к другу, пока не пересекутся. Точка пересечения в итоге будет тот самый пивот,
	 * который при этом может сменить свое положение. После этого алгоритм
	 * рекурсивно запустится для левой и правой части относительно пивота и будет
	 * продолжаться до тех пор, пока весь массив не будет отсортирован.
	 * Если сравнивать данный алгоритм с теми, что были рассмотрены ранее, можно
	 * увидеть, что количество сравнений элементов друг с другом существенно снижено.
	 * Мы так же имеет два вложенных алгоритма. В лучшем из вариантов мы каждый раз
	 * будет делить массив ровно пополам (плюс-минус 1 элемент), что дает
	 * максимальную глубину рекурсии log n. И проводить сравнение элементов друг с
	 * другом в пределах получившихся массивов (O(n)), тем самым получим сложность
	 * O(n log n). В худшем – пивот всегда будет оказываться крайним элементом при
	 * разбиении коллекции (пивот является максимальным или минимальным значением
	 * рассматриваемого массива), а значит максимальная глубина рекурсии будет равна
	 * n-1, что даст сложность O(n2
	 * ). При этом, эффективной сложностью данной
	 * сортировки принято считать именно O(n log n), т.к. шанс попадания пивота крайним
	 * элементом, особенно на большом объеме данных, очень невелик. Это легко
	 * проверить, запустив сортировку того же массива в 1 000 000 элементов, на котором
	 * экспериментировали с пузырьковой сортировкой. Процесс займет не больше
	 * нескольких секунд.
	 */
	public static void quickSort(int[] array, int startPosition, int endPosition) {
		int leftPosition = startPosition;
		int rightPosition = endPosition;
		int pivot = array[(startPosition + endPosition) / 2];
		do {
			while (array[leftPosition] < pivot) {
				leftPosition ++;
			}
			while (array[rightPosition] > pivot) {
				rightPosition --;
			}
			if (leftPosition <= rightPosition) {
				if (leftPosition < rightPosition) {
					int temp = array[leftPosition];
					array[leftPosition] = array[rightPosition];
					array[rightPosition] = temp;
				}
				leftPosition++;
				rightPosition--;
			}
		} while (leftPosition <= rightPosition);
		if (leftPosition < endPosition) {
			quickSort(array,leftPosition,endPosition);
		}
		if (startPosition < rightPosition) {
			quickSort(array,startPosition,rightPosition);
		}
	}

	/**
	 * Сортировка кучей (пирамидальная).
	 *Особенность данной сортировки в использовании дополнительной структуры
	 * данных называемой бинарной кучей (пирамидой).
	 * Бинарная куча представляет из себя древовидную структуру, когда у каждого
	 * объекта может быть до 2 детей. При этом строится из массива она предельно
	 * просто – первый элемент массива является корнем, 2 и 3 его детьми, 4 и 5 детьми
	 * элемента 2 и т.д. пока в массиве остаются элементы.
	 * Благодаря такому подходу к составлению бинарной кучи она получает следующее
	 * свойство: если принять элемент с индексом i за родителя, то индексы его дочерних
	 * элементов будут 2 * i + 1 и 2 * i + 2.
	 * Таким образом, несмотря на то, что сортировка использует для своего алгоритма
	 * бинарную кучу, в реальности строить никакую отдельную структуру данных не
	 * нужно, т.к. мы в любой момент можем определить детей для каждого из элементов
	 * и проводить их сравнение или обмен.
	 * Общая идея сортировки пирамидой заключается в том, что сравнение элементов
	 * происходит не между всеми элементами массива, а только в пределах построенной
	 * пирамидальной структуры, т.е. родителя и его детей. Такая операция называется
	 * «просеиванием», когда интересующий нас узел кучи сравнивается со своими
	 * двумя детьми и меняется местами с тем, что больше родителя. Если оба ребенка
	 * больше родителя – обмен происходит с наибольшим из детей.
	 * Дальше необходимо определить алгоритм, в каком порядке необходимо проводить
	 * операции просеивания. Для этого весь процесс пирамидальной сортировки делится
	 * на 2 этапа.
	 * Первый этап – это подготовка кучи. Определяем правую часть кучи по формуле
	 * n/2-1, где n – длина массива. Начиная с указанного индекса, мы начинаем операции
	 * просеивания в цикле до тех пор, пока не придем к началу массива. В результате
	 * этой операции самый большой элемент нашего массива окажется в индексе 0, что
	 * является вершиной пирамиды.
	 * Второй этап – начинается с того, что первый и последний элемент массива
	 * меняется местами, тем самым наибольший элемент оказывается в конце массива,
	 * а текущая вершина (индекс 0) начинает операцию просеивания по пирамиде с
	 * размером n-1, в результате чего снова наибольший элемент займет 0 индекс нашего
	 * массива. Меняем его местами с предпоследним элементом массива (последний мы
	 * уже определи) и повторяем операцию. Это происходит до тех пор, пока все
	 * элементы массива не займут свое место, а размер пирамиды для просеивания не
	 * уменьшится до 0.
	 * В данном случае мы имеем обратную, в отличии от быстрой сортировки,
	 * зависимость на сложность алгоритма – внешний цикл содержит O(n) шагов, а
	 * вложенный работает по уже знакомому нам принципу O(log n), т.к. количество
	 * операций соответствует обходу вложенных элементов, что суммарно дает нам
	 * сложность O(n log n), как и у быстрой сортировки.
	 */
	public static void heapifySortCreate(int[] array) {
		// Построение кучи (перегруппируем массив).
		for (int i = array.length / 2 - 1; i >= 0; i--){
			heapifySort(array, array.length, i);
		}

		// Один за другим извлекаем элементы из кучи.
		for (int i = array.length - 1; i>= 0; i--) {
			// Перемещаем текущий корень в конец.
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;

			// Вызываем процедуру heapifySort на уменьшенной куче.
			heapifySort(array, i, 0);
		}
	}

	private static void heapifySort(int[] array, int heapSize, int rootIndex) {
		int largest = rootIndex; // Инициализируем наибольший элемент как корень.
		int leftChild = 2 * rootIndex + 1; // Левый = 2 * rootIndex + 1
		int rightChild = 2 * rootIndex + 2; // Правый = 2 * rootIndex +2

		// Если левый дочерний элемент больше корня.
		if (leftChild < heapSize && array[leftChild] > array[largest]) {
			largest = leftChild;
		}

		// Если правый дочерний элемент больше, чем самый большой элемент на данный момент.
		if (rightChild < heapSize && array[rightChild] > array[largest]) {
			largest = rightChild;
		}

		// Если самый большой элемент не корень.
		if (largest != rootIndex) {
			int temp = array[rootIndex];
			array[rootIndex] = array[largest];
			array[largest] = temp;
		}

		// Рекурсивно преобразуем в двоичную кучу затронутое поддерево.
		heapifySort(array, heapSize, largest);
	}
}