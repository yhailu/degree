<!-- Marshall Bowers -->
<?php
    class View_Main_404 extends ViewModel {
        public function view() {
            $this->title = '404 &raquo; CSC417';
            $this->user = Session::get('user');
        }
    }
?>
