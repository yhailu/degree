<!-- Marshall Bowers -->
<?php
    require_once "include/session.php";
    require_once "include/db.php";

    $session = new Session();

    if (!isset($session->user)) {
        header('location: login.php');
        exit();
    }

    DB::init();

    $orders = R::find('order', 'user_id=? order by created_at desc', array($session->user->id));
?>
<?php include "include/header.php"; ?>
<title>Orders &raquo; CSC417</title>
</head>
<body>
    <div class="container">
        <div class="container-fluid">
            <h1>Orders</h1>

            <?php require_once "include/navigation.php"; ?>

            <hr />

            <table class="table table-striped table-condensed">
                <tr>
                    <th>#</th>
                    <th style="min-width: 100px;">Created</th>
                    <th>Details</th>
                </tr>
                <?php foreach ($orders as $order): ?>
                    <tr>
                        <td><a href="order_details.php?id=<?php echo $order->id; ?>"><?php echo $order->id; ?></a></td>
                        <td>
                            <?php
                                $date = new DateTime();
                                $date->setTimestamp($order->created_at);
                                echo $date->format('Y-m-d H:i:s');
                            ?>
                        </td>
                        <td>
                            <?php
                                $items = R::find('item_order', 'order_id=?', array($order->id));

                                foreach ($items as $entry) {
                                    $item = R::findOne('item', 'id=?', array($entry->item_id));
                                    $names[] = $item->name;
                                }

                                echo strlen(join(", ", $names)) < 125 ? join(", ", $names) : (substr(join(", ", $names), 0, 125) . "...");

                                $names = [];
                            ?>
                        </td>
                    </tr>
                <?php endforeach; ?>
            </table>
        </div>
    </div>
</body>
</html>
