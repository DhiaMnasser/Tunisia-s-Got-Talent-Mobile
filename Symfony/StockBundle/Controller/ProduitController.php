<?php

namespace StockBundle\Controller;

use CMEN\GoogleChartsBundle\GoogleCharts\Charts\ColumnChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\Histogram;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\Exception\AccessDeniedException;
use StockBundle\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Produit controller.
 *
 * @Route("Produit")
 */
class ProduitController extends Controller
{
    /**
     * @Route("/stat", name="stat")
     * @return Response
     */

    public function statAction()
    {
        $pieChart = new PieChart();
        $em= $this->getDoctrine();
        $classes = $em->getRepository(Produit::class)->findAll();
        $totalprix=0;
        foreach($classes as $classe) {
            $totalprix=$totalprix+$classe->getQte();
        }

        $data= array();
        $stat=['Prix', 'Qte'];
        $nb=0;
        array_push($data,$stat);
        foreach($classes as $classe) {
            $stat=array();
            array_push($stat,$classe->getNom(),(($classe->getQte()) *100)/$totalprix);
            $nb=($classe->getQte() *100)/$totalprix;
            $stat=[$classe->getNom(),$nb];
            array_push($data,$stat);

        }

        $pieChart->getData()->setArrayToDataTable(
            $data
        );
        $pieChart->getOptions()->setTitle('% des produits avec la Qte par rapport aux prix');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);


        $histogram = new Histogram();
        $histogram->getData()->setArrayToDataTable([
            ['Views'],
            [12000000],
            [13000000],
            [100000000],
            [1000000000],
            [25000000],
            [600000],
            [6000000],
            [65000000],
            [90000000],
            [80000000],
        ]);
        $histogram->getOptions()->setTitle('Views');
        $histogram->getOptions()->setWidth(900);
        $histogram->getOptions()->setHeight(500);
        $histogram->getOptions()->getLegend()->setPosition('none');
        $histogram->getOptions()->setColors(['#e7711c']);
        $histogram->getOptions()->getHistogram()->setLastBucketPercentile(10);
        $histogram->getOptions()->getHistogram()->setBucketSize(10000000);

        $col = new ColumnChart();
        $col->getData()->setArrayToDataTable(
            [
                ['Time of Day', 'Visit Level', ['role' => 'annotation'], 'Buy Level', ['role' => 'annotation']],
                [['v' => [8, 0, 0], 'f' => '8 am'],  1, '1', 0.25, '0.2'],
                [['v' => [9, 0, 0], 'f' => '9 am'],  2, '2',  0.5, '0.5'],
                [['v' => [10, 0, 0], 'f' => '10 am'], 3, '3',    1,  '1'],
                [['v' => [11, 0, 0], 'f' => '11 am'], 4, '4', 2.25,  '2'],
                [['v' => [12, 0, 0], 'f' => '12 am'], 5, '5', 2.25,  '2'],
                [['v' => [13, 0, 0], 'f' => '1 pm'],  6, '6',    3,  '3'],
                [['v' => [14, 0, 0], 'f' => '2 pm'],  7, '7', 3.25,  '3'],
                [['v' => [15, 0, 0], 'f' => '3 pm'],  8, '8',    5,  '5'],
                [['v' => [16, 0, 0], 'f' => '4 pm'],  9, '9',  6.5,  '6'],
                [['v' => [17, 0, 0], 'f' => '5 pm'], 10, '10',  6, '6']
            ]
        );
        $col->getOptions()
            ->setTitle('Visits Throughout the Day')
            ->setWidth(900)
            ->setHeight(500);
        $col->getOptions()
            ->getAnnotations()
            ->setAlwaysOutside(true)
            ->getTextStyle()
            ->setAuraColor('none')
            ->setFontSize(14)
            ->setColor('#000');
        $col->getOptions()
            ->getHAxis()
            ->setFormat('h:mm a')
            ->setTitle('Time of Day')
            ->getViewWindow()
            ->setMin([7, 30, 0])
            ->setMax([17, 30, 0]);
        $col->getOptions()
            ->getVAxis()
            ->setTitle('Rating (scale of 1-10)');

        return $this->render('@Stock/Produit/chart.html.twig', array('piechart' => $pieChart, 'histogram' => $histogram ,'col' => $col));
    }


    /**
     * Lists all Produit entities.
     *
     * @Route("/", name="produit_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $produits = $em->getRepository('StockBundle:Produit')->findAll();

        return $this->render('@Stock/Produit/index.html.twig', array(
            'produits' => $produits,
        ));
    }

    /**
     * Lists all Produit entities.
     *
     * @Route("/index2", name="produit_index")
     * @Method("GET")
     * @param $produits
     * @param $request
     * @return Response
     */
    public function index2Action(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $produits = $em->getRepository('StockBundle:Produit')->findAll();

        $pagination  = $this->get('knp_paginator')->paginate(
            $produits,
            $request->query->get('page', 1)/*le numéro de la page à afficher*/, 4/*nbre d'éléments par page*/  );
        return $this->render('@Stock/Produit/index2.html.twig', array('produits' => $pagination));

    }
    /**
     * Creates a new Produit entity.
     *
     * @Route("/new", name="produit_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
       /* if ($this->get('security.authorization_checker')->isGranted('ROLE_SUPER_ADMIN'))
        {
            throw new AccessDeniedException('accés admin');
        }*/
        $produit = new Produit();
        $form = $this->createForm('StockBundle\Form\ProduitType', $produit);
        $form->handleRequest($request);
        $produit->setEtat('Disponible');
        if ($produit->getQte()>0 && $produit->getPrix()>0){
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();

            return $this->redirectToRoute('produit_show', array('id' => $produit->getId()));
        }}else

        return $this->render('@Stock/Produit/new.html.twig', array(
            'produit' => $produit,
            'form' => $form->createView(),
        ));
    }

    /**
 * Finds and displays a Produit entity.
 *
 * @Route("/{id}", name="produit_show")
 * @Method("GET")
 */
    public function showAction(produit $produit)
    {
        $deleteForm = $this->createDeleteForm($produit);

        return $this->render('@Stock/Produit/show.html.twig', array(
            'produit' => $produit,
            'delete_form' => $deleteForm->createView(),
        ));
    }
    /**
     * Finds and displays a Produit entity.
     *
     * @Route("show2/{id}", name="produit_show")
     * @Method("GET")
     */
    public function show2Action(produit $produit)
    {
        $deleteForm = $this->createDeleteForm($produit);

        return $this->render('@Stock/Produit/show2.html.twig', array(
            'produit' => $produit,
            'delete_form' => $deleteForm->createView(),
        ));
    }
    /**
     * Displays a form to edit an existing Produit entity.
     *
     * @Route("/{id}/edit", name="produit_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, produit $produit)
    {
        /*if ($this->get('security.authorization_checker')->isGranted('ROLE_ADMIN'))
        {
            throw new AccessDeniedException('accés admin');
        }*/
        $deleteForm = $this->createDeleteForm($produit);
        $editForm = $this->createForm('StockBundle\Form\ProduitType', $produit);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('produit_edit', array('id' => $produit->getId()));
        }

        return $this->render('@Stock/Produit/edit.html.twig', array(
            'Produit' => $produit,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a Produit entity.
     *
     * @Route("/{id}/delete", name="produit_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, produit $produit)
    {
        $form = $this->createDeleteForm($produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($produit);
            $em->flush();
        }

        return $this->redirectToRoute('produit_index');
    }

    /**
     * Creates a form to delete a Produit entity.
     *
     * @param produit $produit The Produit entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(produit $produit)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('produit_delete', array('id' => $produit->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }


}
